import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Read-only view of the exact SQLite file used by the connected app. */
public class SQLiteViewer {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.d3d", "false");
        System.setProperty("sun.java2d.opengl", "false");
        Path database = Path.of(args[0]).toAbsolutePath().normalize();
        if (!Files.isRegularFile(database)) throw new IllegalArgumentException("Database not found: " + database);
        SwingUtilities.invokeLater(() -> show(database));
    }

    private static void show(Path database) {
        JFrame window = new JFrame("SQLite Viewer - MoneyTracker database");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(1000, 480);
        window.setLocationByPlatform(true);
        JLabel path = new JLabel("  " + database);
        path.setToolTipText(database.toString());
        JLabel status = new JLabel(" ");
        DefaultTableModel model = new DefaultTableModel() {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable rows = new JTable(model);
        rows.setAutoCreateRowSorter(true);
        rows.setRowHeight(26);
        JButton refresh = new JButton("Refresh now");
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.add(refresh);
        footer.add(status);
        window.add(path, BorderLayout.NORTH);
        window.add(new JScrollPane(rows), BorderLayout.CENTER);
        window.add(footer, BorderLayout.SOUTH);
        Runnable reload = () -> {
            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:"
                    + database.toUri().toASCIIString() + "?mode=ro&busy_timeout=1000");
                 Statement query = connection.createStatement()) {
                Object[] headers = {"ID", "Type", "Description", "Category", "Amount (PHP)", "Date", "Created"};
                java.util.Vector<java.util.Vector<Object>> data = new java.util.Vector<>();
                try (ResultSet result = query.executeQuery("SELECT id,type,description,category,amount_php,"
                        + "transaction_date,created_at FROM transactions ORDER BY id DESC")) {
                    while (result.next()) {
                        java.util.Vector<Object> row = new java.util.Vector<>();
                        for (int i = 1; i <= 7; i++) row.add(result.getObject(i));
                        data.add(row);
                    }
                }
                model.setDataVector(data, new java.util.Vector<>(java.util.Arrays.asList(headers)));
                try (ResultSet totals = query.executeQuery("SELECT * FROM money_summary")) {
                    if (totals.next()) status.setText(String.format(
                        "%d saved rows | Income PHP %.2f | Expenses PHP %.2f | Balance PHP %.2f | Read-only, refreshes every 2 seconds",
                        data.size(), totals.getDouble("total_income_php"), totals.getDouble("total_expenses_php"), totals.getDouble("balance_php")));
                }
            } catch (Exception exception) { status.setText("Could not refresh: " + exception.getMessage()); }
        };
        refresh.addActionListener(event -> reload.run());
        Timer timer = new Timer(2000, event -> reload.run());
        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosed(java.awt.event.WindowEvent event) { timer.stop(); }
        });
        reload.run();
        window.setVisible(true);
        timer.start();
    }
}
