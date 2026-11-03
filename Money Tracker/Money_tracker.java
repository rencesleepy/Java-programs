import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class RecentTransactions {
    public static void updateList(JPanel panel, Transaction_manager manager, 
        Runnable refreshDashboard
    ) {ArrayList<Transaction> transactions = manager.getTransactions();

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

        panel.revalidate();
        panel.repaint();

        JPopupMenu transaction_menu = new JPopupMenu();
        JMenuItem edit_option = new JMenuItem("Edit");
        JMenuItem delete_option = new JMenuItem("Delete");
        transaction_menu.add(edit_option);
        transaction_menu.add(delete_option);

        JButton deleteButton = new JButton("X");
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.decode("#DC2626"));
        deleteButton.setBounds(225, 5, 40, 25);

        panel.add(deleteButton);
        
        deleteButton.addActionListener(e -> {
            transaction_menu.show (
                deleteButton, 
                0, 
                deleteButton.getHeight()
            );
            System.out.println("DELETED: ");
        });

        delete_option.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                panel, 
                "Are you sure to delete this?" , 
                "COnfirm delete", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) 
            {
                boolean deleted = manager.deleteTransaction(transaction);
                if (deleted) {
                    System.out.println("Transaction deleted successfully");
                    refreshDashboard.run();
                }
            }
        });
            y += 55;

            if (y >= panel.getHeight()) {
                break;
            }
        }
    }
}
