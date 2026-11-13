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

    public boolean deleteTransaction(Transaction transaction) {
        return transactions.remove(transaction);
    }
    
    public boolean editTransaction(Transaction oldTransaction, Transaction updatedTransaction) 
    {
        int index = transactions.indexOf(oldTransaction);
        if (index == -1) {
            return false;
        } 

        transactions.set(index, updatedTransaction);
        return true;
    }

    public double getExpenseByCategory(String category) {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction.type.equalsIgnoreCase("EXPENSE") && transaction.category.equalsIgnoreCase(category)) 
            {
                total += transaction.amount;
            }
        }
        return total;
    }

    public double getIncomeByCategory(String category) {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction.type.equalsIgnoreCase("INCOME") && transaction.category.equalsIgnoreCase(category))
            {
                total += transaction.amount;
            }
        }
        return total;
    }

    public double getIncomeByCategoryPercentage(String category) {
        double totalIncome = getTotalIncome();
        if(totalIncome == 0) {
            return 0;
        }

        double categoryIncome = getIncomeByCategory(category);
        return (categoryIncome / totalIncome) * 100;
    }

    public double getCategoryByPercentage(String category) {
        double totalExpenses = getTotalExpenses();
        if (totalExpenses == 0) {
            return 0;
        }
        double categoryExpenses = getExpenseByCategory(category);
        return (categoryExpenses / totalExpenses) * 100;

        /*
        formula: percentage = category expenses * 100 
                              total expenses
        
        */
    }

    public static void main(String[] args) {
        Transaction_manager manager = new Transaction_manager();

        Transaction oldTransaction = new Transaction(
            "EXPENSE" , 
            "Lunch" , 
            "Food" , 
            150.00
        );

        manager.addTransaction(oldTransaction);

        System.out.println("Before editing: " + manager.getTotalExpenses());

        Transaction updatedTransaction = new Transaction(
            "EXPENSE" , 
            "Dinner" , 
            "Food" , 
            250.00
        );

        boolean edited = manager.editTransaction(oldTransaction, updatedTransaction);

        System.out.println("Edited succesfully: " + edited);
        System.out.println("After editing: " + manager.getTotalExpenses());
    }
}
