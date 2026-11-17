import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/** Parameterized SQL keeps typed descriptions and categories separate from SQL. */
public final class TransactionDAO {
    public TransactionDAO() { DatabaseConnection.initialize(); }

    public ArrayList<Transaction> loadAll() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement query = connection.createStatement();
             ResultSet rows = query.executeQuery("SELECT id, type, description, category, amount_php "
                 + "FROM transactions ORDER BY id")) {
            while (rows.next()) {
                Transaction transaction = new Transaction(rows.getString("type"),
                    rows.getString("description"), rows.getString("category"), rows.getDouble("amount_php"));
                transaction.id = rows.getLong("id");
                transactions.add(transaction);
            }
            return transactions;
        } catch (SQLException exception) { throw failure("load transactions", exception); }
    }

    public long insert(Transaction transaction) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement query = connection.prepareStatement(
                 "INSERT INTO transactions (type, description, category, amount_php) VALUES (?, ?, ?, ?)")) {
            connection.setAutoCommit(false);
            bind(query, transaction);
            query.executeUpdate();
            try (Statement statement = connection.createStatement();
                 ResultSet result = statement.executeQuery("SELECT last_insert_rowid()")) {
                if (!result.next()) throw new SQLException("Missing saved transaction ID.");
                long id = result.getLong(1);
                result.close();
                connection.commit();
                return id;
            }
        } catch (SQLException exception) { throw failure("save this transaction", exception); }
    }

    public boolean update(long id, Transaction replacement) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement query = connection.prepareStatement("UPDATE transactions "
                 + "SET type=?, description=?, category=?, amount_php=? WHERE id=?")) {
            bind(query, replacement);
            query.setLong(5, id);
            return query.executeUpdate() == 1;
        } catch (SQLException exception) { throw failure("edit this transaction", exception); }
    }

    public boolean delete(long id) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement query = connection.prepareStatement("DELETE FROM transactions WHERE id=?")) {
            query.setLong(1, id);
            return query.executeUpdate() == 1;
        } catch (SQLException exception) { throw failure("delete this transaction", exception); }
    }

    private static void bind(PreparedStatement query, Transaction transaction) throws SQLException {
        query.setString(1, transaction.type);
        query.setString(2, transaction.description);
        query.setString(3, transaction.category);
        query.setBigDecimal(4, BigDecimal.valueOf(transaction.amount));
    }

    private static DatabaseException failure(String action, SQLException exception) {
        return new DatabaseException("Could not " + action + ". Your change was not saved. "
            + exception.getMessage(), exception);
    }
}
