/*
 * Decompiled with CFR 0.152.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Transaction_history {
    public static void show_history(JFrame jFrame, Transaction_manager transaction_manager) {
        JDialog jDialog = new JDialog(jFrame, "Transaction history", true);
        jDialog.setSize(800, 500);
        jDialog.setLocationRelativeTo(jFrame);
        Object[] objectArray = new String[]{"Type", "Description", "Category", "Amount", "Order"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(objectArray, 0){

            @Override
            public boolean isCellEditable(int n, int n2) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int n) {
                if (n == 3) {
                    return Double.class;
                }
                if (n == 4) {
                    return Integer.class;
                }
                return String.class;
            }
        };
        int n = 0;
        for (Transaction object2 : transaction_manager.getTransactions()) {
            defaultTableModel.addRow(new Object[]{object2.type, object2.description, object2.category, object2.amount, n++});
        }
        JTable jTable = new JTable(defaultTableModel);
        jTable.setFont(new Font("Segoe UI", 0, 14));
        jTable.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer(){

            @Override
            protected void setValue(Object object) {
                if (object instanceof Number) {
                    this.setText(String.format("\u20b1%,.2f", ((Number)object).doubleValue()));
                } else {
                    super.setValue(object);
                }
            }
        });
        jTable.removeColumn(jTable.getColumnModel().getColumn(4));
        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<DefaultTableModel>(defaultTableModel);
        jTable.setRowSorter(tableRowSorter);
        JTextField jTextField = new JTextField();
        jTextField.setFont(new Font("Segoe UI", 0, 15));
        jTextField.setToolTipText("Search transactions by description or category");
        String[] stringArray = new String[]{"All transactions", "Income", "Expenses"};
        JComboBox<String> jComboBox = new JComboBox<String>(stringArray);
        jComboBox.setFont(new Font("Segoe UI", 1, 14));
        jComboBox.setBackground(Color.WHITE);
        jComboBox.setForeground(Color.decode("#0F172A"));
        final Runnable runnable = () -> {
            String string = jTextField.getText().trim();
            String string2 = (String)jComboBox.getSelectedItem();
            ArrayList arrayList = new ArrayList();
            if (!string.isEmpty()) {
                arrayList.add(RowFilter.regexFilter("(?i)" + Pattern.quote(string), 1, 2));
            }
            if (string2.equals("Income")) {
                arrayList.add(RowFilter.regexFilter("^INCOME$", 0));
            } else if (string2.equals("Expenses")) {
                arrayList.add(RowFilter.regexFilter("^EXPENSE$", 0));
            }
            tableRowSorter.setRowFilter(RowFilter.andFilter(arrayList));
        };
        String[] stringArray2 = new String[]{"Newest", "Oldest", "Highest amount", "Lowest amount"};
        JComboBox<String> jComboBox2 = new JComboBox<String>(stringArray2);
        jComboBox2.setFont(new Font("Segoe UI", 1, 15));
        jComboBox2.setBackground(Color.WHITE);
        jComboBox2.setForeground(Color.decode("#0F172A"));
        jComboBox2.addActionListener(actionEvent -> {
            String string = (String)jComboBox2.getSelectedItem();
            if (string.equals("Newest")) {
                tableRowSorter.setSortKeys(List.of(new RowSorter.SortKey(4, SortOrder.DESCENDING)));
            } else if (string.equals("Oldest")) {
                tableRowSorter.setSortKeys(List.of(new RowSorter.SortKey(4, SortOrder.ASCENDING)));
            } else if (string.equals("Highest amount")) {
                tableRowSorter.setSortKeys(List.of(new RowSorter.SortKey(3, SortOrder.DESCENDING)));
            } else if (string.equals("Lowest amount")) {
                tableRowSorter.setSortKeys(List.of(new RowSorter.SortKey(3, SortOrder.ASCENDING)));
            }
        });
        tableRowSorter.setSortKeys(List.of(new RowSorter.SortKey(4, SortOrder.DESCENDING)));
        jTextField.addKeyListener(new KeyAdapter(){

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                runnable.run();
            }
        });
        JPanel jPanel = new JPanel(new BorderLayout(10, 0));
        jPanel.setOpaque(false);
        JPanel jPanel2 = new JPanel(new GridLayout(1, 2, 10, 0));
        jPanel2.setOpaque(false);
        jPanel2.add(jComboBox);
        jPanel2.add(jComboBox2);
        jComboBox.addActionListener(actionEvent -> runnable.run());
        jPanel.add((Component)jTextField, "Center");
        jPanel.add((Component)jPanel2, "East");
        JScrollPane jScrollPane = new JScrollPane(jTable);
        JPanel jPanel3 = new JPanel(new BorderLayout(10, 10));
        jPanel3.setBackground(Color.WHITE);
        jPanel3.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel3.add((Component)jPanel, "North");
        jPanel3.add((Component)jScrollPane, "Center");
        jDialog.setContentPane(jPanel3);
        jDialog.setVisible(true);
    }
}

