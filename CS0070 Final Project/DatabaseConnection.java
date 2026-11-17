import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/** SQLite storage beside the app, with an optional path override for tests. */
public final class DatabaseConnection {
    private DatabaseConnection() {}

    public static Path getDatabasePath() {
        String override = System.getProperty("moneytracker.db");
        if (override != null) return Path.of(override).toAbsolutePath().normalize();
        try {
            Path code = Path.of(DatabaseConnection.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI());
            Path folder = Files.isRegularFile(code) ? code.getParent() : Path.of("");
            return folder.resolve("database/moneytracker.db").toAbsolutePath().normalize();
        } catch (Exception exception) {
            throw new DatabaseException("Cannot locate the database folder.", exception);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + getDatabasePath().toUri().toASCIIString()
            + "?mode=rw&busy_timeout=5000");
    }

    public static void initialize() {
        try {
            Path database = getDatabasePath();
            Files.createDirectories(database.getParent());
            try (InputStream resource = DatabaseConnection.class.getResourceAsStream("/schema.sql")) {
                if (resource == null) throw new SQLException("The bundled database schema is missing.");
                String schema = new String(resource.readAllBytes(), StandardCharsets.UTF_8);
                try (Connection connection = DriverManager.getConnection("jdbc:sqlite:"
                        + database.toUri().toASCIIString() + "?busy_timeout=5000");
                     Statement statement = connection.createStatement()) {
                    // The schema includes BEGIN/COMMIT and only CREATE ... IF NOT EXISTS.
                    for (String sql : schema.split(";")) {
                        if (!sql.trim().isEmpty()) statement.execute(sql);
                    }
                }
            }
        } catch (Exception exception) {
            throw new DatabaseException("Could not open the database at " + getDatabasePath()
                + ". Check that the folder is writable. " + exception.getMessage(), exception);
        }
    }
}
