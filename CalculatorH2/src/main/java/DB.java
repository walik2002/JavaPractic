import java.sql.*;

public class DB {
    private static final String url = "jdbc:h2:file:D:/study/JavaPractic/CalculatorH2/db/database;OLD_INFORMATION_SCHEMA=TRUE";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    //получение подключения к бд
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
