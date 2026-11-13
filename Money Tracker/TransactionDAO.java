import java.sql.SQLException;
import java.util.List;

//ginawa ko to para hindi maghalo yung sql queries at saka sa main gui code nito

public class TransactionDAO {
    public void addTransaction(Transaction transaction) 
    throws SQLException {
        throw new UnsupportedOperationException("databse insrt is not implemented yet");
    }

    //read
    public List<Transaction> getAllTransactions()
    throws SQLException
    {
        throw new UnsupportedOperationException("database select is not iplemented yet");
    }

    public boolean updateTransaction(Transaction transaction)
    throws SQLException {
        throw new UnsupportedOperationException("database update is not implemented yet");
    }

    public boolean deleteTransaction(int transactionId)
    throws SQLException {
        throw new UnsupportedOperationException("databse delete is not implemented yet");
    }
}