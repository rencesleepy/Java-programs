import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RecentTransactions {
    public static void updateList(JPanel panel, ArrayList<Transaction> transactions) 
    {
        panel.removeAll();
        int y =0; 
        for (int i=transactions.size() - 1; i>=0; i--) 
        {
            Transaction transaction = transactions.get(i);
            JLabel description = new JLabel(transaction.description);
            description.setFont(new Font("Segoe UI", Font.BOLD, 14));
            description.setForeground(Color.decode("#0F172A"));
            description.setBounds(5, y, 250, 25);

            JLabel amount = new JLabel(
                String.format (
                    "%s₱%,.2f" , 
                    transaction.type.equalsIgnoreCase("INCOME") ? "+" : "-", 
                    transaction.amount
                )
            );

            amount.setFont(new Font("Segoe UI", Font.BOLD, 13));
            if (transaction.type.equalsIgnoreCase("INCOME")) 
            {
                amount.setForeground(Color.decode("#15803D"));
            }
            else
            {
                amount.setForeground(Color.decode("#DC2626"));
            }
            amount.setBounds(5, y + 22, 250, 25);
            panel.add(description);
            panel.add(amount);

            y += 55;

            if (y >= panel.getHeight()) {
                break;
            }
        }
        panel.revalidate();
        panel.repaint();
    }
}
