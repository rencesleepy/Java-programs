import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//connection to para sa java programs at saka sa SQL database
//moneytracker -> transaction manager -> transactionDAO -> SQl database
//may sample test sa baba

public class Database_connection {
    private static final String URL = System.getenv("URL NITO");

    private static final String USER = System.getenv("USER NAME");

    private static final String PASSWORD = System.getenv("PASSWORD MO");

    public static Connection getConnection() throws SQLException {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new SQLException (
                "Database connection settings missing"
            );
        }
        return DriverManager.getConnection(
            URL, 
            USER, 
            PASSWORD
        );
    }
}



/*

SAMPLE TEST

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
        "jdbc:oracle:thin:@//localhost:1521/XEPDB1";

    private static final String USER =
        "MONEY_TRACKER";

    private static final String PASSWORD =
        System.getenv("MONEY_TRACKER_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        if (PASSWORD == null || PASSWORD.isEmpty()) {
            throw new SQLException(
                "Database password is not configured."
            );
        }

        return DriverManager.getConnection(
            URL,
            USER,
            PASSWORD
        );
    }

    public static void main(String[] args) {

        System.out.println("Connecting to Oracle Database...");

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

 */
