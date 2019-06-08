package mypackage.Util;

public class SQL_Commands {

    public static final String CREATE_MOBILE_NUMBERS =
            "CREATE TABLE IF NOT EXISTS mobile_numbers (" +
                    " id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                    " mobile_number TEXT NOT NULL" +
                    ");";

   public static final String CREATE_NOTEBOOK =
           "CREATE TABLE IF NOT EXISTS notebook (" +
                   " id INTEGER PRIMARY KEY AUTO_INCREMENT," +
                   " first_name TEXT NOT NULL," +
                   " surname TEXT NOT NULL," +
                   " id_mobile_number INTEGER," +
                   " FOREIGN KEY(id_mobile_number) REFERENCES mobile_numbers(id)" +
                   ");";

    public static final String INSERT_MOBILE_NUMBER = "INSERT INTO mobile_numbers"
            + "(mobile_number) VALUES"
            + "(?)";
    public static final String INSERT_PERSON_INTO_NOTEBOOK = "INSERT INTO notebook" +
            "            (first_name, surname, id_mobile_number) VALUES" +
            "            (?, ?, ?)";

    public static final String DELETE_PERSON_FROM_NOTEBOOK = "DELETE FROM notebook WHERE id = ?";
    public static final String DELETE_MOBILE_NUMBER = "DELETE FROM mobile_numbers WHERE id = ?";

    public static final String GET_NOTEBOOK = "SELECT * FROM notebook";
    public static final String GET_MOBILE_NUMBERS = "SELECT * FROM mobile_numbers";

    public static final String GET_MOBILE_NUMBER = "SELECT mobile_number FROM mobile_numbers WHERE id = ?";

    public static final String SHOW_MOBILE_NUMBER_BY_SURNAME = "SELECT notebook.first_name, notebook.surname, mobile_numbers.mobile_number " +
            " FROM notebook INNER JOIN mobile_numbers" +
            " ON notebook.id_mobile_number = mobile_numbers.id" +
            " WHERE surname = ?;";

    public static final String SHOW_SURNAME_BY_MOBILE_NUMBER = "SELECT notebook.first_name, notebook.surname, mobile_numbers.mobile_number" +
            " FROM notebook INNER JOIN mobile_numbers" +
            " ON notebook.id_mobile_number = mobile_numbers.id" +
            " WHERE mobile_number = ?;";

    public static final String UPDATE_NOTEBOOK = " UPDATE notebook SET id_mobile_number = Null" +
            "  WHERE id_mobile_number = ?";

    public static final String GET_LAST_ID = "SELECT MAX(id)" +
            " FROM mobile_numbers";

    public static final String GET_LAST_ID_IN_NOTEBOOK = "SELECT MAX(id)" +
            " FROM notebook";




}
