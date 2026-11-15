
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
        "jdbc:sqlite:database/moneytracker.db";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(URL);
    }

    public static void main(String[] args) {

        System.out.println("Connecting to SQLite Database...");

        try (Connection connection = getConnection()) {

            if (connection.isValid(5)) {

                System.out.println("-----------------------------");
                System.out.println("DATABASE CONNECTED!");
                System.out.println("-----------------------------");

                System.out.println(
                    "Database: " +
                    connection.getMetaData().getDatabaseProductName()
                );

                System.out.println(
                    "Version: " +
                    connection.getMetaData().getDatabaseProductVersion()
                );
            }

        } catch (SQLException e) {

            System.out.println("DATABASE CONNECTION FAILED!");
            System.out.println("Reason: " + e.getMessage());
        }
    }
}
