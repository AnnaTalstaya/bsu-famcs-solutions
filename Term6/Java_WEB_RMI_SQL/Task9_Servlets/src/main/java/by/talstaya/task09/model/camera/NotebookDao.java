package by.talstaya.task09.model.camera;

import by.talstaya.task09.util.SQL_Commands;

import java.sql.*;
import java.util.*;

public class NotebookDao {
    private static volatile NotebookDao instance;
    private List<Person> notebook;

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/notebookdatabase?allowPublicKeyRetrieval=true&useSSL=false";

    static final String USER = "root";
    static final String PASSWORD = "root";

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;


    private NotebookDao() {

        notebook = new ArrayList<>();

        try {
            System.out.println("Connecting");

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            notebook = getNotebook();

            System.out.println("Connected to notebookdatabase");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static NotebookDao getInstance(){
        if(instance == null)
            synchronized (NotebookDao.class){
                if(instance == null)
                    instance = new NotebookDao();
            }
        return instance;
    }

    public List<Person> getNotebook() {

        List<Person> notebook = new ArrayList<>();
        try {

            Statement getNotebookStatement = connection.createStatement();
            ResultSet rs = getNotebookStatement.executeQuery(SQL_Commands.GET_NOTEBOOK);

            PreparedStatement getMobileNumberPrStatement = connection.prepareStatement(SQL_Commands.GET_MOBILE_NUMBER);

            while (rs.next()) {

                getMobileNumberPrStatement.setInt(1, rs.getInt("id_mobile_number"));
                ResultSet rsMobile = getMobileNumberPrStatement.executeQuery();
                String mobileNumber = "";

                if(rsMobile.next()){
                    mobileNumber = rsMobile.getString("mobile_number");
                }
                notebook.add(new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        new MobileNumber(rs.getInt("id_mobile_number"), mobileNumber)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return notebook;
    }

    public synchronized List<Person> find(String query) {
        List<Person> shownNotebook;

        if(query!= null) {
            shownNotebook = showMobileNumberBySurname(query);
        }else shownNotebook = notebook;

        return shownNotebook;
    }

    public ArrayList<Person> showMobileNumberBySurname(String surname){
        ArrayList<Person> people = new ArrayList<Person>();
        try{
            PreparedStatement showPrStatement = connection.prepareStatement(SQL_Commands.SHOW_MOBILE_NUMBER_BY_SURNAME);
            showPrStatement.setString(1, surname);

            ResultSet rs = showPrStatement.executeQuery();

            while(rs.next()){
                people.add(new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        new MobileNumber(rs.getInt("id_mobile_number"), rs.getString("mobile_number"))
                ));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return people;
    }

    public ArrayList<Person> showSurnameByMobileNumber(String mobile_number){
        ArrayList<Person> surnames = new ArrayList<Person>();
        try{
            PreparedStatement showPrStatement = connection.prepareStatement(SQL_Commands.SHOW_SURNAME_BY_MOBILE_NUMBER);
            showPrStatement.setString(1, mobile_number);

            ResultSet rs = showPrStatement.executeQuery();

            while(rs.next()){
                surnames.add(new Person(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        new MobileNumber(rs.getInt("id_mobile_number"), rs.getString("mobile_number"))
                ));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        surnames.forEach(person -> {
            System.out.println(person);
        });
        return surnames;
    }

    public void deletePerson(int personId) {
        try {

            PreparedStatement deleteFromNotebookStatement = connection.prepareStatement(SQL_Commands.DELETE_PERSON_FROM_NOTEBOOK);
            deleteFromNotebookStatement.setInt(1, personId);
            deleteFromNotebookStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertPerson(String firstName, String surname, String mobileNumber) {
        try {
            PreparedStatement insertStatementM = connection.prepareStatement(SQL_Commands.INSERT_MOBILE_NUMBER);
            insertStatementM.setString(1, mobileNumber);
            insertStatementM.executeUpdate();

            PreparedStatement prStatement = connection.prepareStatement(SQL_Commands.GET_ID_MOBILE);
            prStatement.setString(1, mobileNumber);
            ResultSet res = prStatement.executeQuery();
            int idMob = 0;
            if(res.next()){
                idMob = res.getInt("id");
            }


            PreparedStatement insertStatement = connection.prepareStatement(SQL_Commands.INSERT_PERSON_INTO_NOTEBOOK);
            ResultSet rs = prStatement.executeQuery();

            insertStatement.setString(1, firstName);
            insertStatement.setString(2, surname);
            insertStatement.setInt(3, idMob);
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
