import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.util.function.DoubleSupplier;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** One chart comparing income and expenses as shares of their combined amount. */
public final class IncomeExpenseChart extends JPanel {
    private static final Color INCOME_COLOR = Color.decode("#15803D");
    private static final Color EXPENSE_COLOR = Color.decode("#DC2626");
    private final DoubleSupplier incomeSource;
    private final DoubleSupplier expenseSource;
    private final JLabel incomeDetail = new JLabel();
    private final JLabel expenseDetail = new JLabel();
    private double incomePercentage;
    private double expensePercentage;
    private boolean hasData;

    public IncomeExpenseChart(DoubleSupplier incomeSource, DoubleSupplier expenseSource) {
        this.incomeSource = incomeSource;
        this.expenseSource = expenseSource;
        setLayout(null);
        setOpaque(false);
        getAccessibleContext().setAccessibleName("Income and expenses chart");
        addLegend("Income", INCOME_COLOR, incomeDetail, 40);
        addLegend("Expenses", EXPENSE_COLOR, expenseDetail, 115);
        JLabel explanation = new JLabel("Share of income + expenses");
        explanation.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        explanation.setForeground(Color.decode("#64748B"));
        explanation.setBounds(315, 195, 285, 25);
        add(explanation);
        refresh();
    }

    private void addLegend(String name, Color color, JLabel detail, int y) {
        JPanel swatch = new JPanel();
        swatch.setBackground(color);
        swatch.setBounds(290, y + 5, 14, 14);
        add(swatch);
        JLabel title = new JLabel(name);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(Color.decode("#0F172A"));
        title.setBounds(315, y, 265, 25);
        add(title);
        detail.setFont(new Font("Segoe UI", Font.BOLD, 13));
        detail.setForeground(Color.decode("#475569"));
        detail.setBounds(315, y + 28, 285, 25);
        detail.getAccessibleContext().setAccessibleName(name + " amount and percentage");
        add(detail);
    }

    public void refresh() {
        double income = incomeSource.getAsDouble();
        double expenses = expenseSource.getAsDouble();
        if (!Double.isFinite(income) || !Double.isFinite(expenses) || income < 0 || expenses < 0)
            throw new IllegalArgumentException("Chart totals must be finite and non-negative.");
        hasData = income > 0 || expenses > 0;
        if (hasData) {
            // Scaling avoids overflowing when adding two large totals.
            double scale = Math.max(income, expenses);
            incomePercentage = (income / scale) / (income / scale + expenses / scale) * 100;
            expensePercentage = 100 - incomePercentage;
        } else {
            incomePercentage = 0;
            expensePercentage = 0;
        }
        incomeDetail.setText(String.format("\u20b1%,.2f (%.1f%%)", income, incomePercentage));
        expenseDetail.setText(String.format("\u20b1%,.2f (%.1f%%)", expenses, expensePercentage));
        getAccessibleContext().setAccessibleDescription(
            "Income " + incomeDetail.getText() + "; expenses " + expenseDetail.getText());
        repaint();
    }

    public double getIncomePercentage() { return incomePercentage; }
    public double getExpensePercentage() { return expensePercentage; }

    @Override protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
        try {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            double incomeAngle = incomePercentage * 3.6;
            double expenseAngle = hasData ? 360 - incomeAngle : 0;
            if (hasData) {
                g.setColor(INCOME_COLOR);
                g.fill(new Arc2D.Double(30, 15, 200, 200, 90, -incomeAngle, Arc2D.PIE));
                g.setColor(EXPENSE_COLOR);
                g.fill(new Arc2D.Double(30, 15, 200, 200, 90 - incomeAngle, -expenseAngle, Arc2D.PIE));
            } else {
                g.setColor(Color.decode("#E2E8F0"));
                g.fillOval(30, 15, 200, 200);
            }
            g.setColor(Color.WHITE);
            g.fillOval(85, 70, 90, 90);
            g.setFont(new Font("Segoe UI", Font.BOLD, 12));
            g.setColor(Color.WHITE);
            if (incomePercentage >= 10) drawPercentage(g, incomePercentage, 90 - incomeAngle / 2);
            if (expensePercentage >= 10) drawPercentage(g, expensePercentage, 90 - incomeAngle - expenseAngle / 2);
        } finally { g.dispose(); }
    }

    private static void drawPercentage(Graphics2D g, double value, double angle) {
        String label = String.format("%.1f%%", value);
        double radians = Math.toRadians(angle);
        FontMetrics metrics = g.getFontMetrics();
        int x = (int) Math.round(130 + 73 * Math.cos(radians) - metrics.stringWidth(label) / 2.0);
        int y = (int) Math.round(115 - 73 * Math.sin(radians)
            + (metrics.getAscent() - metrics.getDescent()) / 2.0);
        g.drawString(label, x, y);
    }
}
