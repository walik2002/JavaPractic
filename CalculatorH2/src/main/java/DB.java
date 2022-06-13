import java.sql.*;

public class DB {
    private static final String URL = "jdbc:h2:file:D:/study/JavaPractic/CalculatorH2/db/database;OLD_INFORMATION_SCHEMA=TRUE";

    //получение подключения к бд
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
