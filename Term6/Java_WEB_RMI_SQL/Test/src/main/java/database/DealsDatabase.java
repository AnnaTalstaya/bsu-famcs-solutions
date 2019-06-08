package database;


import model.Deal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealsDatabase {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/dealsdatabase?allowPublicKeyRetrieval=true&useSSL=false";

    static final String USER = "root";
    static final String PASSWORD = "root";

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    final static String GET_DEALS = "SELECT * FROM dealsdatabase.deals;";
    final static String GET_COUNT = "SELECT COUNT(*) AS num FROM dealsdatabase.deals\n" +
            "WHERE typeDeal=\"Buy\";";
    final static String GET_COMMON_SUM = "SELECT SUM(sumDeal) AS summ \n" +
            "FROM dealsdatabase.deals\n"  +
            "WHERE typeDeal=\"Buy\";";

    public DealsDatabase() {
        try {
            System.out.println("Connecting");

            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            System.out.println("Connected to by.talstaya.test.database");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Deal> getDeals() {
        List<Deal> deals = new ArrayList<Deal>();
        try {
            ps = connection.prepareStatement(GET_DEALS);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idStudent = rs.getInt("idDeal");
                String typeDeal = rs.getString("typeDeal");
                String title = rs.getString("title");
                String dateDeal = rs.getString("dateDeal");
                int sumDeal = rs.getInt("sumDeal");
                deals.add(new Deal(idStudent, typeDeal, title, dateDeal, sumDeal));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deals;
    }

    public int getCount() {
        try {
            ps = connection.prepareStatement(GET_COUNT);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("num");
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public int getCommonSum() {
        try {
            ps = connection.prepareStatement(GET_COMMON_SUM);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("summ");
            }
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

}
