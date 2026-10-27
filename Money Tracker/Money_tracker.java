import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Money_tracker_organized {
    public static void main(String[] args) {
        // 1. MAIN WINDOW
        JFrame MAIN_INTERFACE = new JFrame();
        MAIN_INTERFACE.setTitle("Money Tracker");
        MAIN_INTERFACE.setSize(1100, 800);
        MAIN_INTERFACE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MAIN_INTERFACE.setLocationRelativeTo(null);
        MAIN_INTERFACE.setLayout(null);
        MAIN_INTERFACE.getContentPane().setBackground(Color.decode("#101827"));

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
        //-------------------------------------------------- EXPENSES MONEY

        // 3. ROUNDED CARDS
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

        // 4. CARD POSITIONS AND STYLES

        box_card.setOpaque(false);
        income_card.setOpaque(false);
        expenses_card.setOpaque(false);

        box_card.setBounds(35, 110, 1000, 150);
        income_card.setBounds(35, 280, 480, 100);
        expenses_card.setBounds(535, 280, 500, 100);

        box_card.setLayout(null);
        income_card.setLayout(null);
        expenses_card.setLayout(null);

        // 5. LABEL POSITIONS
        MyWallet.setBounds(50, 30, 300, 50);
        total_balance.setBounds(25, 15, 300, 40);
        amount.setBounds(25, 50, 400, 50);
        available_balance.setBounds(25, 100, 300, 35);
        income.setBounds(25, 1, 300, 40);
        expenses.setBounds(25, 1, 300, 40);

        // 6. LOWER PANELS
        JLabel breakdown = new JLabel("Total spending breakdown");
        breakdown.setFont(new Font("Segoe UI", Font.BOLD, 20));
        breakdown.setForeground(Color.WHITE);
        breakdown.setBounds(20, 10, 400, 40);

        JLabel recent_transactions = new JLabel("Recent Transactions");
        recent_transactions.setFont(new Font("Segoe UI", Font.BOLD, 20));
        recent_transactions.setForeground(Color.WHITE);
        recent_transactions.setBounds(20, 10, 260, 40);

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
        chart.setBounds(35, 400, 670, 340);
        chart.setBackground(Color.decode("#1E293B"));
        chart.setLayout(null);
        chart.setOpaque(false);

        recents.setBounds(730, 400, 300, 340);
        recents.setBackground(Color.decode("#1E293B"));
        recents.setLayout(null);
        recents.setOpaque(false);

        // 7. ADD TRANSACTION BUTTON
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
                g11.dispose();
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


        // 8. TRANSACTION FORM
        JDialog transaction_form = new JDialog(
            MAIN_INTERFACE, 
            "Add Transaction", 
            true
        );
        transaction_form.setSize(1070, 650);
        transaction_form.setLocationRelativeTo(MAIN_INTERFACE);
        transaction_form.setLayout(null);
        transaction_form.getContentPane().setBackground(Color.decode("#101827"));

        JLabel newtransaction_text = new JLabel("New transaction");
        newtransaction_text.setBounds(40, 30, 400, 30);
        newtransaction_text.setFont(new Font("Segoe UI", Font.BOLD, 20));
        newtransaction_text.setForeground(Color.WHITE);
        transaction_form.add(newtransaction_text);

        JLabel transaction_type = new JLabel("Type of transaction");
        transaction_type.setBounds(40, 65, 400, 30);
        transaction_type.setFont(new Font ("Segoe UI", Font.PLAIN, 16));
        transaction_type.setForeground(Color.WHITE);
        transaction_form.add(transaction_type);

        JPanel transactionform_border = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g15) {
                java.awt.Graphics2D g16 = (java.awt.Graphics2D) g15.create();

                g16.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON  
                );

                GradientPaint gradient_expenses = new GradientPaint(
                    0, 0, Color.decode("#3b4857"), 
                    getWidth(), getHeight(), Color.decode("#292c42")
                );
                g16.setPaint(gradient_expenses);

                g16.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(),
                    25, 25
                );
                g16.dispose();
            }
        };
            transactionform_border.setBounds(20, 20, 1015, 575);
            transactionform_border.setLayout(null);
            transactionform_border.setOpaque(false);

            transaction_form.add(transactionform_border);

            transaction_process.addActionListener(e -> {
                transaction_form.setVisible(true);     
        });

        JButton income_record = new JButton("+ Income") { // INCOME CLICK
            @Override 
            protected void paintComponent(java.awt.Graphics g17) {
                java.awt.Graphics2D g18 = (java.awt.Graphics2D) g17.create();

                g18.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );
                
                g18.setColor(getBackground());
                g18.fillRoundRect(
                    0, 0,
                    getWidth(), getHeight(),
                    25, 25
                );
                super.paintComponent(g17);
            }
        };
        income_record.setFont(new Font("Segoe UI", Font.BOLD, 15));
        income_record.setBackground(Color.decode("#1ea00d"));
        income_record.setForeground(Color.WHITE);
        income_record.setFocusPainted(false);
        income_record.setBounds(10, 80, 490, 45);
        income_record.setContentAreaFilled(false);
        income_record.setOpaque(false);
        income_record.setBorderPainted(false);
        income_record.addActionListener(e -> {
            System.out.println("INCOME RECORDED");
        });

        transactionform_border.add(income_record);

        JButton expense_record = new JButton("+ Expense") { // EXPENSE CLICK
            @Override 
            protected void paintComponent(java.awt.Graphics g19) {
                java.awt.Graphics2D g20 = (java.awt.Graphics2D) g19.create();

                g20.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );

                g20.setColor(getBackground());
                g20.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(), 
                    25, 25
                );
                super.paintComponent(g19);
            }
        };
        expense_record.setFont(new Font("Segoe UI" , Font.BOLD, 15));
        expense_record.setBackground(Color.decode("#a00d0d"));
        expense_record.setForeground(Color.WHITE);
        expense_record.setFocusPainted(false);
        expense_record.setBounds(510, 80, 490, 45);
        expense_record.setContentAreaFilled(false);
        expense_record.setBorderPainted(false);
        expense_record.addActionListener(e -> {
            System.out.println("EXPENSE RECORDED");
        });

        transactionform_border.add(expense_record);

        // 8.1 INPUTS
        JLabel description_label = new JLabel("Description");
        description_label.setForeground(Color.WHITE);
        description_label.setBounds(25, 145, 250, 30);
        transactionform_border.add(description_label);

        JLabel amount_label = new JLabel("Amount");
        amount_label.setForeground(Color.WHITE);
        amount_label.setBounds(25, 230, 250, 30);
        transactionform_border.add(amount_label);

        JTextField description_input = new JTextField() {
            @Override 
            protected void paintComponent(java.awt.Graphics g21) {
                java.awt.Graphics2D g22 = (java.awt.Graphics2D) g21.create();
                g22.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );
                g22.setColor(getBackground());
                g22.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(), 
                    25, 25
                );
                super.paintComponent(g21);
            }
        };
        
        description_input.setFont (new Font("Segoe UI", Font.PLAIN, 16));
        description_input.setBounds(20, 180, 970, 40);
        description_input.setBackground(Color.decode("#626f77"));
        description_input.setOpaque(false);
        description_input.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)
        );

        JTextField AmountSet = new JTextField() {
            @Override 
            protected void paintComponent(java.awt.Graphics g23) {
                java.awt.Graphics2D g24 = (java.awt.Graphics2D) g23.create();
                g24.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );

                g24.setColor(getBackground());
                g24.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(), 
                    25, 25
                );
                super.paintComponent(g23);
            }
        };

        AmountSet.setFont (new Font("Segoe UI", Font.PLAIN, 16));
        AmountSet.setBounds(20, 260, 970, 40);
        AmountSet.setBackground(Color.decode("#626f77"));
        AmountSet.setOpaque(false);
        AmountSet.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)
        );

        transactionform_border.add(AmountSet);
        transactionform_border.add(description_input);

        // 9. ADD COMPONENTS TO THEIR CONTAINERS
        box_card.add(total_balance);
        box_card.add(amount);
        box_card.add(available_balance);
        income_card.add(income);
        income_card.add(income_amount);
        expenses_card.add(expenses);
        expenses_card.add(expenses_amount);
        chart.add(breakdown);
        recents.add(recent_transactions);

        MAIN_INTERFACE.add(MyWallet);
        MAIN_INTERFACE.add(box_card);
        MAIN_INTERFACE.add(income_card);
        MAIN_INTERFACE.add(expenses_card);
        MAIN_INTERFACE.add(chart);
        MAIN_INTERFACE.add(recents);
        MAIN_INTERFACE.add(transaction_process);

        // 10. DISPLAY WINDOW
        MAIN_INTERFACE.setVisible(true);
        System.out.println("SUCCESSFUL EXECUTION!");
    }
}
