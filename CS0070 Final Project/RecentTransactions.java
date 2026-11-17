/*
 * Decompiled with CFR 0.152.
 */
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class RecentTransactions {
    public static void updateList(JPanel jPanel, Transaction_manager transaction_manager, Runnable runnable) {
        ArrayList<Transaction> arrayList = transaction_manager.getTransactions();
        jPanel.removeAll();
        int n = 0;
        for (int i = arrayList.size() - 1; i >= 0; --i) {
            final Transaction transaction = arrayList.get(i);
            JLabel jLabel = new JLabel(transaction.description);
            jLabel.setFont(new Font("Segoe UI", 1, 14));
            jLabel.setForeground(Color.decode("#0F172A"));
            jLabel.setBounds(5, n, 250, 25);
            JLabel jLabel2 = new JLabel(transaction.category);
            jLabel2.setFont(new Font("Segoe UI", 1, 12));
            jLabel2.setForeground(Color.decode("#64748B"));
            jLabel2.setBounds(5, n + 22, 200, 20);
            jPanel.add(jLabel2);
            JLabel jLabel3 = new JLabel(String.format("%s\u20b1%,.2f", transaction.type.equalsIgnoreCase("INCOME") ? "+" : "-", transaction.amount));
            jLabel3.setFont(new Font("Segoe UI", 1, 13));
            if (transaction.type.equalsIgnoreCase("INCOME")) {
                jLabel3.setForeground(Color.decode("#15803D"));
            } else {
                jLabel3.setForeground(Color.decode("#DC2626"));
            }
            jLabel3.setBounds(5, n + 40, 250, 25);
            jPanel.add(jLabel);
            jPanel.add(jLabel3);
            jPanel.revalidate();
            jPanel.repaint();
            JPopupMenu jPopupMenu = new JPopupMenu();
            JMenuItem jMenuItem = new JMenuItem("Edit");
            jMenuItem.addActionListener(actionEvent2 -> {
                final JDialog jDialog = new JDialog(SwingUtilities.getWindowAncestor(jPanel), "edit transaction", Dialog.ModalityType.APPLICATION_MODAL);
                jDialog.setSize(500, 400);
                jDialog.setLocationRelativeTo(jPanel);
                JPanel jPanel2 = new JPanel(new GridLayout(4, 2, 15, 15));
                jPanel2.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
                JLabel descriptionLabel = new JLabel("description: ");
                JTextField jTextField = new JTextField(transaction.description);
                JLabel amountLabel = new JLabel("Amount: ");
                JTextField jTextField2 = new JTextField(String.valueOf(transaction.amount));
                JLabel categoryLabel = new JLabel("Category");
                final JTextField jTextField3 = new JTextField(transaction.category);
                jTextField3.setEditable(false);
                final String[] stringArray = new String[]{"Salary", "Allowance", "Business", "Freelance", "Gifts", "Investments", "Refunds", "Others"};
                final String[] stringArray2 = new String[]{"Food", "Transportation", "Shopping", "Bills", "Entertainment", "Health", "Education", "Others"};
                final JDialog jDialog2 = new JDialog(jDialog, "Select category", true);
                jDialog2.setSize(500, 450);
                jDialog2.setLocationRelativeTo(jDialog);
                final JPanel jPanel3 = new JPanel(new GridLayout(4, 2, 15, 15));
                jPanel3.setBackground(Color.decode("#F8FAFC"));
                jPanel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                jTextField3.addMouseListener(new MouseAdapter(){

                    @Override
                    public void mouseClicked(MouseEvent mouseEvent) {
                        jPanel3.removeAll();
                        String[] stringArray3 = transaction.type.equalsIgnoreCase("INCOME") ? stringArray : stringArray2;
                        for (String string : stringArray3) {
                            JButton jButton = new JButton(string);
                            jButton.setFont(new Font("Segoe UI", 1, 15));
                            jButton.setBackground(Color.decode("#E8E9FF"));
                            jButton.setForeground(Color.decode("#0F172A"));
                            jButton.setFocusPainted(false);
                            jButton.addActionListener(actionEvent -> {
                                jTextField3.setText(string);
                                jDialog2.setVisible(false);
                            });
                            jPanel3.add(jButton);
                        }
                        jPanel3.revalidate();
                        jPanel3.repaint();
                        jDialog2.setLocationRelativeTo(jDialog);
                        jDialog2.setVisible(true);
                    }
                });
                jDialog2.setContentPane(jPanel3);
                JButton jButton = new JButton("Save changes");
                jButton.setBackground(Color.decode("#4F46E5"));
                jButton.setForeground(Color.WHITE);
                jButton.setFocusPainted(false);
                jButton.addActionListener(actionEvent -> {
                    double d;
                    String string = jTextField.getText().trim();
                    String string2 = jTextField2.getText().trim();
                    String string3 = jTextField3.getText().trim();
                    if (string.isEmpty() || string2.isEmpty() || string3.isEmpty()) {
                        JOptionPane.showMessageDialog(jDialog, "Please fill all fields", "Input error", 0);
                        return;
                    }
                    try {
                        d = Transaction_manager.validateAmount(string2);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {
                        JOptionPane.showMessageDialog(jDialog, illegalArgumentException.getMessage(), "Invalid amount", 0);
                        return;
                    }
                    Transaction transaction2 = new Transaction(transaction.type, string, string3, d);
                    boolean bl;
                    try {
                        bl = transaction_manager.editTransaction(transaction, transaction2);
                    } catch (DatabaseException | IllegalArgumentException exception) {
                        JOptionPane.showMessageDialog(jDialog, exception.getMessage(), "Could not save", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (bl) {
                        System.out.println("transaction updated successfully");
                        jDialog.dispose();
                        runnable.run();
                    } else {
                        JOptionPane.showMessageDialog(jTextField2, "transaction not found", "Invalid", 0);
                    }
                });
                jPanel2.add(descriptionLabel);
                jPanel2.add(jTextField);
                jPanel2.add(amountLabel);
                jPanel2.add(jTextField2);
                jPanel2.add(categoryLabel);
                jPanel2.add(jTextField3);
                jPanel2.add(new JLabel(""));
                jPanel2.add(jButton);
                jDialog.setContentPane(jPanel2);
                jDialog.setVisible(true);
            });
            JMenuItem jMenuItem2 = new JMenuItem("Delete");
            jPopupMenu.add(jMenuItem);
            jPopupMenu.add(jMenuItem2);
            JButton jButton = new JButton("X");
            jButton.setFont(new Font("Segoe UI", 1, 12));
            jButton.setForeground(Color.WHITE);
            jButton.setBackground(Color.decode("#DC2626"));
            jButton.setBounds(205, n + 5, 45, 25);
            jPanel.add(jButton);
            jButton.addActionListener(actionEvent -> {
                jPopupMenu.show(jButton, 0, jButton.getHeight());
                System.out.println("DELETED: ");
            });
            jMenuItem2.addActionListener(actionEvent -> {
                boolean bl;
                int confirmation = JOptionPane.showConfirmDialog(jPanel, "Are you sure to delete this?", "COnfirm delete", 0, 2);
                if (confirmation != 0) return;
                try {
                    bl = transaction_manager.deleteTransaction(transaction);
                } catch (DatabaseException exception) {
                    JOptionPane.showMessageDialog(jPanel, exception.getMessage(), "Could not delete", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (bl) {
                    System.out.println("Transaction deleted successfully");
                    runnable.run();
                }
            });
            n += 75;
        }
        jPanel.setPreferredSize(new Dimension(250, Math.max(n, 235)));
        jPanel.revalidate();
        jPanel.repaint();
    }
}

