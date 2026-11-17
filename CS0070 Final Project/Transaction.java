/*
 * Decompiled with CFR 0.152.
 */
public class Transaction {
    long id; // SQLite row ID; zero until saved.
    String type;
    String description;
    String category;
    double amount;

    public Transaction(String string, String string2, String string3, double d) {
        this.type = string;
        this.description = string2;
        this.category = string3;
        this.amount = d;
    }
}

