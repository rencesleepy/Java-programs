import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Dashboard extends JFrame {

    public Dashboard() {
        // 1. MAIN WINDOW
        setTitle("Money Tracker");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#101827"));

        // 2. LABELS AND AMOUNTS
        JLabel MyWallet = new JLabel("My Wallet");
        MyWallet.setFont(new Font("Segoe UI", Font.BOLD, 35));
        MyWallet.setForeground(Color.WHITE);

        JLabel total_balance = new JLabel("Total Balance");
        total_balance.setFont(new Font("Segoe UI", Font.PLAIN, 25)); 
        total_balance.setForeground(Color.WHITE);

        JLabel amount = new JLabel("-------------");
        amount.setFont(new Font("Segoe UI", Font.BOLD, 30)); // yung mismong pera
        amount.setForeground(Color.WHITE);

        JLabel available_balance = new JLabel("Available Balance");
        available_balance.setFont(new Font("Segoe UI", Font.BOLD, 20));
        available_balance.setForeground(Color.decode("#4ADE80"));

        JLabel income = new JLabel("Income");
        income.setFont(new Font("Segoe UI", Font.BOLD, 20));
        income.setForeground(Color.decode("#4ADE80"));

        JLabel expenses = new JLabel("Expenses");
        expenses.setFont(new Font("Segoe UI", Font.BOLD, 20));
        expenses.setForeground(Color.decode("#FCA5A5"));

        //-------------------------------------------------- INCOME MONEY
        JLabel income_amount = new JLabel("₱0.00");
        income_amount.setFont(new Font("Segoe UI", Font.BOLD, 25));
        income_amount.setForeground(Color.WHITE);
        income_amount.setBounds(25, 33, 300, 50);

        JLabel expenses_amount = new JLabel("₱0.00");
        expenses_amount.setFont(new Font("Segoe UI", Font.BOLD, 25));
        expenses_amount.setForeground(Color.WHITE);
        expenses_amount.setBounds(25, 33, 300, 50);

        MyWallet.setBounds(50, 30, 300, 50);
        add(MyWallet);

            JPanel box_card = new JPanel() { // para mag round
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();

                g2.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );

            GradientPaint gradient = new GradientPaint (
                0, 0, Color.decode("#315A9C"), 
                getWidth(), getHeight(), Color.decode("#4a525f")
            );

                g2.setPaint(gradient);

                g2.fillRoundRect(
                    0, 0, // null to
                    getWidth(), getHeight(),
                    25, 25
                );

            g2.dispose();
            }
        };
        box_card.setBounds(35, 110, 1000, 150);
        box_card.setLayout(null);
        box_card.setOpaque(false);

        total_balance.setBounds(25, 15, 300, 40);
        amount.setBounds(25, 50, 400, 50);
        available_balance.setBounds(25, 100, 300, 35);

        box_card.add(total_balance);
        box_card.add(amount);
        box_card.add(available_balance);
        add(box_card);



        JPanel income_card = new JPanel() { // income box
            @Override
            protected void paintComponent(java.awt.Graphics g1) {
                java.awt.Graphics2D g3 = (java.awt.Graphics2D) g1.create();

                g3.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );

                GradientPaint gradient_income = new GradientPaint (
                0, 0, Color.decode("#20594B"), 
                getWidth(), getHeight(), Color.decode("#102F2B")
            );

                g3.setPaint(gradient_income);

                g3.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(), 
                    25, 25
                );
            g3.dispose();
            }
        };
        income_card.setBounds(35, 200, 480, 100);
        income_card.setLayout(null);
        income_card.setOpaque(false);
        income.setBounds(25, 1, 300, 40);
        income_card.add(income);
        income_card.add(income_amount);
        add(income_card);

    }
}