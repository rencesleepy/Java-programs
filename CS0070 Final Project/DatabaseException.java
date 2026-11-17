/** An operation failed before it could be saved to the database. */
public class DatabaseException extends RuntimeException {
    public DatabaseException(String message, Throwable cause) { super(message, cause); }
}
