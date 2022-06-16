import java.sql.*;
import java.util.Scanner;
public class Client {
    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection;
    private static Statement statement;
    public static void main(String[] args) throws SQLException {
        System.out.println("Enter the value of the first currency in the format $10 or 10rub");
        String firstValue = inputCurrency();
        System.out.println("Enter the value of the second currency in the format $10 or 10rub");
        String secondValue = inputCurrency();

        //выбор валюты которая получится в резултате
        int resultCurrency;
        while (true) {
            System.out.println("""
                                Select the currency in which the result will be
                                1. Dollar
                                2. Rubles
                                """);
            resultCurrency = scanner.nextInt();
            if (resultCurrency == 1 || resultCurrency == 2)
                break;
        }

        //выбор операции
        int operation;

        while (true) {
            System.out.println("""
                    Select an operation
                    1. Addition
                    2. Subtraction
                    """);
            operation = scanner.nextInt();
            if (operation == 1 || operation == 2)
                break;
        }
        //Создание экземпляра объекта выражения, которое выводится на экран и записывается в бд
        Expression expression = new Expression(firstValue, secondValue, operation, resultCurrency);
        System.out.println(expression.getExpression());
        //Получение строки с выражением
        String ex = expression.getExpression();
        //Получение текущего времени
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //Sql-запрос
        String sql = String.format("INSERT INTO OperationHistory (EXPRESSION,DATE) VALUES ('%s','%s')",ex,timestamp);
        System.out.println(sql);
        try {
            //Запись в БД
            connection = DB.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            statement.close();
            connection.close();
        }


        //Возможность использовать операцию еще раз
        while (true) {
            System.out.println("Do you want to perform another operation? Y/N");
            String ans = scanner.next();
            if (ans.equalsIgnoreCase("N"))
                return;
            if (ans.equalsIgnoreCase("Y"))
                break;
        }
    }

    public static String inputCurrency() {
        //Ввод значений испоьзуемой валюты в указанном формате
        String value = scanner.nextLine();
        while (!Money.isCurrencyFormat(value)) {
            System.out.println("""
                    The dollar value is indicated by the $ symbol placed before the number (for example, $57.75).
                    The value in rubles is the symbol "rub" located after the number (for example, 57.75rub).
                    Enter the correct value!!!
                    """);
            value = scanner.nextLine();
        }
        return value;
        ///git use
    }
}

