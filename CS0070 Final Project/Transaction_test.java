public class Transaction_test {
    public static void main(String[] args) throws Exception {
        System.setProperty("moneytracker.db", java.nio.file.Files.createTempDirectory(
            "moneytracker-example-").resolve("test.db").toString());

        Transaction_manager manager = new Transaction_manager();

        manager.addTransaction(
            new Transaction("INCOME", "Allowance", "Personal", 1500)
        );

        manager.addTransaction(
            new Transaction("INCOME", "Part-time Job", "Salary", 500)
        );

        manager.addTransaction(
            new Transaction("EXPENSE", "Lunch", "Food", 150)
        );

        manager.addTransaction(
            new Transaction("EXPENSE", "Transportation", "Travel", 50)
        );

        manager.addTransaction(
            new Transaction("EXPENSE", "Shopping", "Personal", 300)
        );

        System.out.println("Total Income: " + manager.getTotalIncome());
        System.out.println("Total Expenses: " + manager.getTotalExpenses());
        System.out.println("Balance: " + manager.getBalance());

        Transaction lunch = new Transaction(
            "EXPENSE" , "LUNCH" , "FOOD" , 150
        );

        manager.addTransaction(lunch);
        System.out.println("Before deletion: " + manager.getTotalExpenses());

        manager.deleteTransaction(lunch);
        System.out.println("After deletion: " + manager.getTotalExpenses());
    }
}
