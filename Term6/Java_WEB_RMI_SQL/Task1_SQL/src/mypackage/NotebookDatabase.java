package mypackage;

import mypackage.Util.SQL_Commands;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotebookDatabase {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/notebookdatabase?serverTimezone=Europe/Moscow&useSSL=false";

    static final String USER = "root";
    static final String PASSWORD = "root";

    private Connection connection;
    private PreparedStatement ps;

    public NotebookDatabase() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            createTables();
            System.out.println("Connected to database");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTables() {
        try {
            Statement createMobileNumbersStatement = connection.createStatement();
            createMobileNumbersStatement.execute(SQL_Commands.CREATE_MOBILE_NUMBERS);

            Statement createNotebookStatement = connection.createStatement();
            createNotebookStatement.execute(SQL_Commands.CREATE_NOTEBOOK);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertPerson(Person person) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement(SQL_Commands.INSERT_PERSON_INTO_NOTEBOOK);
            insertStatement.setString(1, person.getFirstName());
            insertStatement.setString(2, person.getSurname());
            insertStatement.setInt(3, person.getMobileNumberId());
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertMobileNumber(String mobileNumber) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Commands.INSERT_MOBILE_NUMBER);
            preparedStatement.setString(1, mobileNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        new MobileNumber(mobileNumber)
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return notebook;
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

    public void deleteMobileNumber(int mobileNumberID) {
        try {

            PreparedStatement updateNotebookStatement = connection.prepareStatement(SQL_Commands.UPDATE_NOTEBOOK);
            updateNotebookStatement.setInt(1, mobileNumberID);
            updateNotebookStatement.executeUpdate();

            PreparedStatement deleteStatement = connection.prepareStatement(SQL_Commands.DELETE_MOBILE_NUMBER);
            deleteStatement.setInt(1, mobileNumberID);
            deleteStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Person> showMobileNumberBySurname(String surname){
        ArrayList<Person> people = new ArrayList<Person>();
        try{
            PreparedStatement showPrStatement = connection.prepareStatement(SQL_Commands.SHOW_MOBILE_NUMBER_BY_SURNAME);
            showPrStatement.setString(1, surname);

            ResultSet rs = showPrStatement.executeQuery();

            while(rs.next()){
                people.add(new Person(
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        new MobileNumber(rs.getString("mobile_number"))
                ));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return people;
    }

    public ArrayList<String> showSurnameByMobileNumber(String mobile_number){
        ArrayList<String> surnames = new ArrayList<String>();
        try{
            PreparedStatement showPrStatement = connection.prepareStatement(SQL_Commands.SHOW_SURNAME_BY_PHONE_NUMBER);
            showPrStatement.setString(1, mobile_number);

            ResultSet rs = showPrStatement.executeQuery();

            while(rs.next()){
                surnames.add(rs.getString("surname"));
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return surnames;
    }

    public List<MobileNumber> getMobileNumbers(){
        List<MobileNumber> mobileNumbers = new ArrayList<>();
        try {
            Statement getMobileNumbersStatement = connection.createStatement();
            ResultSet rs = getMobileNumbersStatement.executeQuery(SQL_Commands.GET_MOBILE_NUMBERS);

            while (rs.next()) {
                mobileNumbers.add(
                        new MobileNumber(rs.getString("mobile_number"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mobileNumbers;
    }

}
