package by.talstaya.test.database;

import by.talstaya.test.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsDatabase {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/studentsdatabase?serverTimezone=Europe/Moscow&useSSL=false";

    static final String USER = "root";
    static final String PASSWORD = "root";

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    final static  String GET_STUDENTS = "SELECT * FROM studentsdatabase.students;";

    public StudentsDatabase() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            System.out.println("Connected to by.talstaya.test.database");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Student> getStudents() {
        List<Student> students = new ArrayList<Student>();
        try {
            ps = connection.prepareStatement(GET_STUDENTS);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idStudent = rs.getInt("idStudent");
                String fullName = rs.getString("fullName");
                int course = rs.getInt("course");
                int group = rs.getInt("groupNumber");
                double averageScore = rs.getDouble("averageScore");
                boolean fromVillage = rs.getBoolean("fromVillage");

                students.add(new Student(idStudent, fullName, course, group, averageScore, fromVillage));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

}
