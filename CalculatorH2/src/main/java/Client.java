import java.sql.*;
import java.util.Currency;
import java.util.Scanner;
import java.lang.Object;
public class Client {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter the value of the first currency in the format $10 or 10rub");
        String first_value = inputCurrency();
        System.out.println("Enter the value of the second currency in the format $10 or 10rub");
        String second_value = inputCurrency();

        //выбор валюты которая получится в резултате
        int result_currency;
        while (true) {
            System.out.println("Select the currency in which the result will be" +
                    "\n 1. Dollar" +
                    "\n 2. Rubles");
            result_currency = scanner.nextInt();
            if (result_currency == 1 || result_currency == 2)
                break;
        }

        //выбор операции
        int operation;

        while (true) {
            System.out.println("Select an operation" + "" +
                    "\n1. Addition" +
                    "\n2. Subtraction");
            operation = scanner.nextInt();
            if (operation == 1 || operation == 2)
                break;
        }
        //Создание экземпляра объекта выражения, которое выводится на экран и записывается в бд
        Expression expression = new Expression(first_value, second_value, operation, result_currency);
        System.out.println(expression.getExpression());
        //Получение строки с выражением
        String ex = expression.getExpression();
        //Получение текущего времени
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //Sql-запрос
        String SQL = String.format("INSERT INTO OperationHistory (EXPRESSION,DATE) VALUES ('" + ex + "', " + "'" + timestamp + "')");
        System.out.println(SQL);
        try {
            //Запись в БД
            Connection connection = DB.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(SQL);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
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
            System.out.println("The dollar value is indicated by the $ symbol placed before the number (for example, $57.75)." +
                    "\n The value in rubles is the symbol \"rub\" located after the number (for example, 57.75rub)." +
                    "\n Enter the correct value!!!");
            value = scanner.nextLine();
        }
        return value;
        ///git use
    }
}

