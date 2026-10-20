import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Money_tracker {
    public static void main(String[] args) {
        JFrame MAIN_INTERFACE = new JFrame();
        MAIN_INTERFACE.getContentPane().setBackground(Color.decode("#101827")); // main window

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
        //-------------------------------------------------- EXPENSES MONEY

        JPanel expenses_card = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g5) {
                java.awt.Graphics2D g4 = (java.awt.Graphics2D) g5.create();

                g4.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );

                GradientPaint gradient_expenses = new GradientPaint (
                0, 0, Color.decode("#693B4A"), 
                getWidth(), getHeight(), Color.decode("#35212D")
            );

                g4.setPaint(gradient_expenses);

                g4.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(), 
                    25, 25
                );
            g4.dispose();
            }
        };


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

        //-------------------------------- CARDS
        box_card.setBounds(35, 110, 1000, 150);
        box_card.setLayout(null);
        box_card.setBackground(Color.decode("#1E293B"));

        income_card.setBounds(35, 280, 480, 100);
        income_card.setLayout(null);
        income_card.setBackground(Color.decode("#1E293B"));

        expenses_card.setBounds(535, 280, 500, 100);
        expenses_card.setLayout(null);
        expenses_card.setBackground(Color.decode("#1E293B"));

        //---------------------------------

        income_card.add(income);
        income_card.add(income_amount);

        MAIN_INTERFACE.setTitle("Money Tracker");
        MAIN_INTERFACE.setSize(1100, 800);
        MAIN_INTERFACE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MAIN_INTERFACE.setLocationRelativeTo(null);
        MAIN_INTERFACE.setLayout(null);

        MyWallet.setBounds(50, 30, 300, 50);
        total_balance.setBounds(25, 15, 300, 40);
        amount.setBounds(25, 50, 400, 50);
        available_balance.setBounds(25, 100, 300, 35);
        income.setBounds(25, 1, 300, 40);
        expenses.setBounds(25, 1, 300, 40);

        expenses_card.add(expenses);
        expenses_card.add(expenses_amount);

        MAIN_INTERFACE.add(MyWallet);
        MAIN_INTERFACE.add(box_card);
        MAIN_INTERFACE.add(expenses_card);
        MAIN_INTERFACE.add(income_card);

        box_card.add(total_balance);
        box_card.add(amount);
        box_card.add(available_balance);

        //---------------------------------- lower panels

        JLabel breakdown = new JLabel("Total spending breakdown");
        breakdown.setFont(new Font("Segoe UI", Font.BOLD, 20));
        breakdown.setForeground(Color.WHITE);
        breakdown.setBounds(20, 10, 400, 40);

        JLabel recent_transactions = new JLabel("Recent Transactions");
        recent_transactions.setFont(new Font("Segoe UI", Font.BOLD, 20));
        recent_transactions.setForeground(Color.WHITE);
        recent_transactions.setBounds(20, 10, 400, 40);

        JPanel chart = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g7) {
                java.awt.Graphics2D g6 = (java.awt.Graphics2D) g7.create();

                g6.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );

                g6.setColor(getBackground());

                g6.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(), 
                    25, 25
                );
            g6.dispose();
            }
        };
        chart.setBounds(35, 400, 670, 340);
        chart.setBackground(Color.decode("#1E293B"));

        JPanel recents = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g8) {
                java.awt.Graphics2D g9 = (java.awt.Graphics2D) g8.create();

                g9.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );
                
                g9.setColor(getBackground());

                g9.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(),
                    25, 25
                );
            g9.dispose();
            }
        };

        JButton transaction_process = new JButton("+ Add Transaction") {
            @Override
            protected void paintComponent(java.awt.Graphics g10) {
                java.awt.Graphics2D g11 = (java.awt.Graphics2D) g10.create();
                

                g11.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON  
                );

                g11.setColor(getBackground());

                g11.fillRoundRect(
                    0, 0,
                    getWidth(), getHeight(),
                    25, 25
                );
                super.paintComponent(g10);
            }
        };
        transaction_process.setFont(new Font("Segoe UI", Font.BOLD, 15));
        transaction_process.setBackground(Color.decode("#2563EB"));
        transaction_process.setForeground(Color.WHITE);
        transaction_process.setFocusPainted(false);
        transaction_process.setBounds(830, 35, 205, 45);
        transaction_process.setContentAreaFilled(false);
        transaction_process.setOpaque(false);
        transaction_process.setBorderPainted(false);
        transaction_process.addActionListener(e -> {
            System.out.println("responded");
        });

        recents.setBounds(730, 400, 300, 340);
        recents.setBackground(Color.decode("#1E293B"));

        chart.add(breakdown);
        chart.setLayout(null);

        recents.add(recent_transactions);
        recents.setLayout(null);

        MAIN_INTERFACE.add(chart);
        MAIN_INTERFACE.add(recents);
        
        box_card.setOpaque(false);
        income_card.setOpaque(false);
        expenses_card.setOpaque(false);
        chart.setOpaque(false);
        recents.setOpaque(false);


        MAIN_INTERFACE.add(transaction_process);
        MAIN_INTERFACE.setVisible(true);

        System.out.println("SUCESSFULL EXECUTION!");
    }
}
