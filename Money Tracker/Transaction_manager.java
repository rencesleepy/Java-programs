import java.util.ArrayList;
public class Transaction_manager {
    private ArrayList<Transaction> transactions = new ArrayList<>(); 
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public double getTotalIncome() {
        double total = 0; 
        for (Transaction transaction : transactions) {
            if (transaction.type.equals("INCOME")) {
                total += transaction.amount;
            }
        }
        return total;
    }

    public double getTotalExpenses() {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction.type.equals("EXPENSE")) {
                total += transaction.amount;
            }
        }
        return total;
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpenses();
    }

    public static double validateAmount(String amountstr) {
        double money = Double.parseDouble(amountstr.trim());

        if(!Double.isFinite(money) || money <= 0) {
            throw new IllegalArgumentException(
                "Amount must not below zero"
            );
        }
        return money;
    }

    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
    
}
