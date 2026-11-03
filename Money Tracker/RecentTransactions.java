import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

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
        edit_option.addActionListener(e -> {
            JDialog edit_dialog = new JDialog(
                javax.swing.SwingUtilities.getWindowAncestor(panel),
                "edit transaction", 
                java.awt.Dialog.ModalityType.APPLICATION_MODAL
            );

            edit_dialog.setSize(500, 400);
            edit_dialog.setLocationRelativeTo(panel);

            JPanel edit_panel = new JPanel(
                new GridLayout(4, 2, 15, 15)
            );

            edit_panel.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25)
            );

            JLabel description_label = new JLabel("description: ");
            JTextField edit_description = new JTextField(
                transaction.description
            );

            JLabel amount_label = new JLabel("Amount: ");

            JTextField edit_amount = new JTextField(
                String.valueOf(transaction.amount)
            );

            JLabel category_label = new JLabel("Category");

            JTextField edit_category = new JTextField(
                transaction.category
            );

            JButton save_change = new JButton("Save changes");
            save_change.setBackground(Color.decode("#4F46E5"));
            save_change.setForeground(Color.WHITE);
            save_change.setFocusPainted(false);

            edit_panel.add(description_label);
            edit_panel.add(edit_description);
            edit_panel.add(amount_label);
            edit_panel.add(edit_amount);
            edit_panel.add(category_label);
            edit_panel.add(edit_category);
            edit_panel.add(new JLabel(""));
            edit_panel.add(save_change);

            edit_dialog.setContentPane(edit_panel);
            edit_dialog.setVisible(true);
        });
        JMenuItem delete_option = new JMenuItem("Delete");
        transaction_menu.add(edit_option);
        transaction_menu.add(delete_option);

        JButton deleteButton = new JButton("X");
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.decode("#DC2626"));
        deleteButton.setBounds(220, y + 5, 45, 25);

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
        panel.revalidate();
        panel.repaint();
    }
}
