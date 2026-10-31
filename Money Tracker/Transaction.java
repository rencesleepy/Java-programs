public class Transaction{
    String type;
    String description;
    String category;
    double amount;

    public Transaction(String type, String description, 
        String category, double amount) {
        this.type = type;
        this.description = description;
        this.category = category;
        this.amount = amount;
    };
}
