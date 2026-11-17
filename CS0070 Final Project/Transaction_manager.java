/*
 * Decompiled with CFR 0.152.
 */
import java.util.ArrayList;

public class Transaction_manager {
    private final TransactionDAO database = new TransactionDAO();
    private final ArrayList<Transaction> transactions = database.loadAll();

    public void addTransaction(Transaction transaction) {
        validateTransaction(transaction);
        if (transaction.id != 0) throw new IllegalArgumentException("This transaction is already saved.");
        transaction.id = database.insert(transaction);
        this.transactions.add(transaction);
    }

    public double getTotalIncome() {
        double d = 0.0;
        for (Transaction transaction : this.transactions) {
            if (!transaction.type.equals("INCOME")) continue;
            d += transaction.amount;
        }
        return d;
    }

    public double getTotalExpenses() {
        double d = 0.0;
        for (Transaction transaction : this.transactions) {
            if (!transaction.type.equals("EXPENSE")) continue;
            d += transaction.amount;
        }
        return d;
    }

    public double getBalance() {
        return this.getTotalIncome() - this.getTotalExpenses();
    }

    public static double validateAmount(String string) {
        double d = Double.parseDouble(string.trim());
        if (!Double.isFinite(d) || d < 0.01 || d > 999999999999.99
                || java.math.BigDecimal.valueOf(d).stripTrailingZeros().scale() > 2) {
            throw new IllegalArgumentException("Enter PHP 0.01 to 999,999,999,999.99 with at most two decimal places.");
        }
        return d;
    }

    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<Transaction>(this.transactions);
    }

    public boolean deleteTransaction(Transaction transaction) {
        if (!transactions.contains(transaction)) return false;
        if (!database.delete(transaction.id)) return false;
        return transactions.remove(transaction);
    }

    public boolean editTransaction(Transaction transaction, Transaction transaction2) {
        int n = this.transactions.indexOf(transaction);
        if (n == -1) {
            return false;
        }
        validateTransaction(transaction2);
        if (transaction2 != transaction && transaction2.id != 0)
            throw new IllegalArgumentException("The replacement is already saved.");
        if (!database.update(transaction.id, transaction2)) return false;
        transaction2.id = transaction.id;
        this.transactions.set(n, transaction2);
        return true;
    }

    private static void validateTransaction(Transaction transaction) {
        if (transaction == null || !("INCOME".equals(transaction.type) || "EXPENSE".equals(transaction.type)))
            throw new IllegalArgumentException("Choose INCOME or EXPENSE.");
        if (transaction.description == null || transaction.description.trim().isEmpty()
                || transaction.category == null || transaction.category.trim().isEmpty())
            throw new IllegalArgumentException("Description and category are required.");
        validateAmount(Double.toString(transaction.amount));
    }

    public double getExpenseByCategory(String string) {
        double d = 0.0;
        for (Transaction transaction : this.transactions) {
            if (!transaction.type.equalsIgnoreCase("EXPENSE") || !transaction.category.equalsIgnoreCase(string)) continue;
            d += transaction.amount;
        }
        return d;
    }

    public double getIncomeByCategory(String string) {
        double d = 0.0;
        for (Transaction transaction : this.transactions) {
            if (!transaction.type.equalsIgnoreCase("INCOME") || !transaction.category.equalsIgnoreCase(string)) continue;
            d += transaction.amount;
        }
        return d;
    }

    public double getIncomeByCategoryPercentage(String string) {
        double d = this.getTotalIncome();
        if (d == 0.0) {
            return 0.0;
        }
        double d2 = this.getIncomeByCategory(string);
        return d2 / d * 100.0;
    }

    public double getCategoryByPercentage(String string) {
        double d = this.getTotalExpenses();
        if (d == 0.0) {
            return 0.0;
        }
        double d2 = this.getExpenseByCategory(string);
        return d2 / d * 100.0;
    }

    public static void main(String[] stringArray) {
        Transaction_manager transaction_manager = new Transaction_manager();
        Transaction transaction = new Transaction("EXPENSE", "Lunch", "Food", 150.0);
        transaction_manager.addTransaction(transaction);
        System.out.println("Before editing: " + transaction_manager.getTotalExpenses());
        Transaction transaction2 = new Transaction("EXPENSE", "Dinner", "Food", 250.0);
        boolean bl = transaction_manager.editTransaction(transaction, transaction2);
        System.out.println("Edited succesfully: " + bl);
        System.out.println("After editing: " + transaction_manager.getTotalExpenses());
    }
}

