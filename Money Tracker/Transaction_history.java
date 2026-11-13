import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Transaction_history {
    public static void show_history (JFrame parent, Transaction_manager manager) {
        JDialog history_dialog = new JDialog(parent, "Transaction history" , true);

        history_dialog.setSize(800, 500);
        history_dialog.setLocationRelativeTo(parent);

        String[] columns = {
            "Type" , 
            "Description" , 
            "Category" , 
            "Amount" , 
            "Order"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            @Override 
            public Class<?> getColumnClass(int column) {
                if (column == 3) {
                    return Double.class;
                }

                if (column == 4) {
                    return Integer.class;
                }
                return String.class;
            }
        };

        int order = 0;

        for (Transaction transaction : manager.getTransactions()) {
            model.addRow(new Object[] {
                transaction.type, 
                transaction.description, 
                transaction.category, 
                transaction.amount,
                order++
            });
        }

        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI" , Font.PLAIN, 14));
        table.getColumnModel().getColumn(3).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override 
            protected void setValue (Object value) {
                if (value instanceof Number) {
                    setText(
                        String.format(
                            "₱%,.2f", ((Number) value).doubleValue()
                        )
                    );
                }
                else 
                {
                    super.setValue(value);
                }
            } // DITO KANA TUMIGIL
        });

        table.removeColumn(
            table.getColumnModel().getColumn(4)
        );

        // row sort
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JTextField search_field = new JTextField();
        search_field.setFont(new Font("Segoe UI" , Font.PLAIN, 15));
        search_field.setToolTipText("Search transactions by description or category");

        String[] filter_choices = { // drop down
            "All transactions" , 
            "Income" , 
            "Expenses"
        };

        JComboBox<String> filter_transactions = new JComboBox<>(filter_choices);
        filter_transactions.setFont(new Font("Segoe UI" , Font.BOLD, 14));
        filter_transactions.setBackground(Color.WHITE);
        filter_transactions.setForeground(Color.decode("#0F172A"));

        Runnable updateFilter = () -> {
            String search = search_field.getText().trim();
            String selected_filter = (String) filter_transactions.getSelectedItem();
            java.util.List<RowFilter<Object, Object>> filters = new java.util.ArrayList<>();

            if (!search.isEmpty()) {
                filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(search), 1, 2));
            }

            if (selected_filter.equals("Income"))
            {
                filters.add(RowFilter.regexFilter("^INCOME$", 0));
            }
            else if (selected_filter.equals("Expenses"))
            {
                filters.add(RowFilter.regexFilter("^EXPENSE$", 0));
            }

            sorter.setRowFilter(RowFilter.andFilter(filters));

        };

        String[] sort_choices = {
            "Newest" ,
            "Oldest" , 
            "Highest amount" ,
            "Lowest amount"  
        };

        JComboBox <String> sort_transactions = new JComboBox<>(sort_choices);
        sort_transactions.setFont(new Font("Segoe UI" , Font.BOLD, 15));
        sort_transactions.setBackground(Color.WHITE);
        sort_transactions.setForeground(Color.decode("#0F172A"));
        sort_transactions.addActionListener(e -> {
            String selected_sort = (String) sort_transactions.getSelectedItem();
            if (selected_sort.equals("Newest")) {
                sorter.setSortKeys(
                    java.util.List.of(
                        new javax.swing.RowSorter.SortKey(4, javax.swing.SortOrder.DESCENDING)
                    )
                );
            }
            else if (selected_sort.equals("Oldest")) {
                sorter.setSortKeys(
                    java.util.List.of(
                        new javax.swing.RowSorter.SortKey(4, javax.swing.SortOrder.ASCENDING)
                    )
                );
            }
            else if (selected_sort.equals("Highest amount")) {
                sorter.setSortKeys(
                    java.util.List.of(
                        new javax.swing.RowSorter.SortKey(3, javax.swing.SortOrder.DESCENDING)
                    )
                );
            }
            else if (selected_sort.equals("Lowest amount")) {
                sorter.setSortKeys(
                    java.util.List.of(
                        new javax.swing.RowSorter.SortKey(3, javax.swing.SortOrder.ASCENDING)
                    )
                );
            }
        });

        sorter.setSortKeys(
            java.util.List.of(
                new javax.swing.RowSorter.SortKey(4, javax.swing.SortOrder.DESCENDING)
            )
        );

        search_field.addKeyListener(new KeyAdapter() {
            @Override 
            public void keyReleased(KeyEvent e) {
                updateFilter.run();
            }
        });

        JPanel search_filter_panel = new JPanel(new BorderLayout(10, 0));
        search_filter_panel.setOpaque(false);

        JPanel filter_sort_panel = new JPanel(new java.awt.GridLayout(1, 2, 10, 0));
        filter_sort_panel.setOpaque(false);

        filter_sort_panel.add(filter_transactions);
        filter_sort_panel.add(sort_transactions);

        filter_transactions.addActionListener(e -> {
            updateFilter.run();
        });

        search_filter_panel.add(search_field, BorderLayout.CENTER);
        search_filter_panel.add(filter_sort_panel, BorderLayout.EAST);

        JScrollPane scroll = new JScrollPane(table);
        JPanel history_panel = new JPanel(new BorderLayout(10, 10));
        history_panel.setBackground(Color.WHITE);
        history_panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        history_panel.add(search_filter_panel, BorderLayout.NORTH);
        history_panel.add(scroll, BorderLayout.CENTER);
        history_dialog.setContentPane(history_panel);
        history_dialog.setVisible(true);
    }
}
