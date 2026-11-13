import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Money_tracker_organized {
    public static void main(String[] args) {

        //connector
        Transaction_manager manager = new Transaction_manager();
        final String[] selected_type = {"NONE"};
        final String[] selected_chart = {"EXPENSE"};

        // 1. MAIN WINDOW
        JFrame MAIN_INTERFACE = new JFrame();
        MAIN_INTERFACE.setTitle("Money Tracker");
        MAIN_INTERFACE.setSize(1100, 800);
        MAIN_INTERFACE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MAIN_INTERFACE.setLocationRelativeTo(null);
        MAIN_INTERFACE.setLayout(null);
        MAIN_INTERFACE.getContentPane().setBackground(Color.decode("#F1F5F9"));

        // 2. LABELS AND AMOUNTS
        JLabel MyWallet = new JLabel("My Wallet");
        MyWallet.setFont(new Font("Segoe UI", Font.BOLD, 35));
        MyWallet.setForeground(Color.decode("#0F172A"));
        

        JLabel total_balance = new JLabel("Total Balance");
        total_balance.setFont(new Font("Segoe UI", Font.PLAIN, 25)); 
        total_balance.setForeground(Color.decode("#E0E7FF"));

        JLabel amount = new JLabel("-------------");
        amount.setFont(new Font("Segoe UI", Font.BOLD, 30)); // yung mismong pera
        amount.setForeground(Color.WHITE);

        JLabel available_balance = new JLabel("Available Balance");
        available_balance.setFont(new Font("Segoe UI", Font.BOLD, 20));
        available_balance.setForeground(Color.decode("#BBF7D0"));

        JLabel income = new JLabel("\u2197 Income");
        income.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
        income.setForeground(Color.decode("#166534"));

        JLabel expenses = new JLabel("↘ Expenses");
        expenses.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
        expenses.setForeground(Color.decode("#991B1B"));

        //-------------------------------------------------- INCOME MONEY
        JLabel income_amount = new JLabel("₱0.00");
        income_amount.setFont(new Font("Segoe UI", Font.BOLD, 25));
        income_amount.setForeground(Color.decode("#166534"));
        income_amount.setBounds(25, 33, 300, 50);

        JLabel expenses_amount = new JLabel("₱0.00");
        expenses_amount.setFont(new Font("Segoe UI", Font.BOLD, 25));
        expenses_amount.setForeground(Color.decode("#991B1B"));
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
                0, 0, Color.decode("#FEE2E2"), 
                getWidth(), getHeight(), Color.decode("#FECACA")
            );

                g4.setPaint(gradient_expenses);

                g4.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(), 
                    25, 25
                );

                // border ng expense
                g4.setColor(Color.decode("#CBD5E1"));
                g4.setStroke(new java.awt.BasicStroke(2));
                g4.drawRoundRect(
                    1, 1, 
                    getWidth() -3, getHeight() -3, 
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
                0, 0, Color.decode("#DCFCE7"), 
                getWidth(), getHeight(), Color.decode("#BBF7D0")
            );

                g3.setPaint(gradient_income);

                g3.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(), 
                    25, 25
                );

                // border delkjfdaeda
                g3.setColor(Color.decode("#CBD5E1"));
                g3.setStroke(new java.awt.BasicStroke(2));
                g3.drawRoundRect(
                    1, 1, 
                    getWidth() -3, getHeight() -3, 
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
                0, 0, Color.decode("#4F46E5"), 
                getWidth(), getHeight(), Color.decode("#2563EB")
            );

                g2.setPaint(gradient); 

                g2.fillRoundRect(
                    1, 1, // null to
                    getWidth() -3 , getHeight() -3,
                    25, 25
                );
                // border frfrfrfrfr
                g2.setColor(Color.decode("#CBD5E1"));
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.drawRoundRect(
                    1, 1, 
                    getWidth() -3, getHeight() -3,
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


        JPanel spending_chart = new JPanel() {

            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);

                java.awt.Graphics2D g2 =
                    (java.awt.Graphics2D) g.create();

                g2.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );

                // 1. CHECK SELECTED CHART
                boolean isIncome = selected_chart[0].equals("INCOME");

                // 2. SELECT CATEGORIES
                String[] categories;

                if (isIncome) {
                    categories = new String[] {
                        "Salary",
                        "Allowance",
                        "Business",
                        "Freelance",
                        "Gifts",
                        "Investments",
                        "Refunds",
                        "Others"
                    };
                } else {
                    categories = new String[] {
                        "Food",
                        "Transportation",
                        "Shopping",
                        "Bills",
                        "Entertainment",
                        "Health",
                        "Education",
                        "Others"
                    };
                }

                // 3. CATEGORY COLORS
                Color[] colors = {
                    Color.decode("#4F46E5"),
                    Color.decode("#F59E0B"),
                    Color.decode("#10B981"),
                    Color.decode("#F43F5E"),
                    Color.decode("#8B5CF6"),
                    Color.decode("#06B6D4"),
                    Color.decode("#EAB308"),
                    Color.decode("#64748B")
                };

                // 4. GET TOTAL AMOUNT
                double total = isIncome
                    ? manager.getTotalIncome()
                    : manager.getTotalExpenses();

                // 5. DRAW DOUGHNUT CHART
                if (total > 0) {

                    int startAngle = 90;
                    int previousAngle = 0;

                    for (int i = 0; i < categories.length; i++) {

                        // GET CATEGORY AMOUNT
                        double categoryAmount = isIncome
                            ? manager.getIncomeByCategory(categories[i])
                            : manager.getExpenseByCategory(categories[i]);

                        if (categoryAmount <= 0) {
                            continue;
                        }

                        // CALCULATE SEGMENT ANGLE
                        int endAngle = (int) Math.round(
                            previousAngle +
                            (categoryAmount / total) * 360
                        );

                        int angle = endAngle - previousAngle;

                        // DRAW CATEGORY SEGMENT
                        g2.setColor(colors[i]);

                        g2.fillArc(
                            30, 15,
                            200, 200,
                            startAngle,
                            -angle
                        );

                        startAngle -= angle;
                        previousAngle = endAngle;
                    }

                    // 6. DRAW WHITE CENTER
                    g2.setColor(Color.WHITE);

                    g2.fillOval(
                        85, 70,
                        90, 90
                    );
                }

                g2.dispose();
            }
        };

        

        spending_chart.setBounds(20, 90, 300, 240);
        spending_chart.setOpaque(false);

        JPanel category_legend = new JPanel();
        category_legend.setLayout(null);
        category_legend.setOpaque(false);
        category_legend.setBounds(320, 100, 320, 220);

        // update category legend
        Runnable updateLegend = () -> {
            category_legend.removeAll();

            boolean isIncome = selected_chart[0].equals("INCOME");

            String[] categories = isIncome ? new String[] {
                "Salary",
                "Allowance",
                "Business",
                "Freelance",
                "Gifts",
                "Investments",
                "Refunds",
                "Others"
            } : new String[] {
                "Food",
                "Transportation",
                "Shopping",
                "Bills",
                "Entertainment",
                "Health",
                "Education",
                "Others"
            };
            Color[] colors = {
                Color.decode("#4F46E5"),
                Color.decode("#F59E0B"),
                Color.decode("#10B981"),
                Color.decode("#F43F5E"),
                Color.decode("#8B5CF6"),
                Color.decode("#06B6D4"),
                Color.decode("#EAB308"),
                Color.decode("#64748B"),
                Color.decode("#1a3357"),
                Color.decode("#719e67"),
                Color.decode("#7a6a73")
            };
            Double total = isIncome ? manager.getTotalIncome() : manager.getTotalExpenses();
            int y = 0;

            for (int i=0; i<categories.length; i++) {
                double categoryAmount = isIncome ? manager.getIncomeByCategory(categories[i]) : manager.getExpenseByCategory(categories[i]);
                if (categoryAmount <= 0) {
                    continue;
                }

                double percentage = (categoryAmount / total) * 100;

                //color category
                JPanel colorbox = new JPanel();
                colorbox.setBackground(colors[i]);
                colorbox.setBounds(5, y + 5, 12, 12);

                JLabel category_name = new JLabel(categories[i]);
                category_name.setFont(new Font("Segoe UI" , Font.BOLD, 13));
                category_name.setForeground(Color.decode("#0F172A"));
                category_name.setBounds(25, y, 150, 22);

                JLabel categoryAmoundLabel = new JLabel(String.format("₱%,.2f (%.1f%%)", categoryAmount, percentage));

                categoryAmoundLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
                categoryAmoundLabel.setForeground(Color.decode("#64748B"));
                categoryAmoundLabel.setBounds(25, y+20, 250, 20);

                category_legend.add(colorbox);
                category_legend.add(category_name);
                category_legend.add(categoryAmoundLabel);

                y += 50;
            }
            category_legend.revalidate();
            category_legend.repaint();
        };

        JLabel breakdown_statement = new JLabel("No spending data yet");
        breakdown_statement.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        breakdown_statement.setForeground(Color.GRAY);
        breakdown_statement.setBounds(22, 50, 200, 40);

        JLabel recent_transactions_statement = new JLabel("No transactions");
        recent_transactions_statement.setFont(new Font("Segoe UI" , Font.PLAIN, 15));
        recent_transactions_statement.setForeground(Color.GRAY);
        recent_transactions_statement.setBounds(22, 50, 200, 40);

        JLabel breakdown = new JLabel("Total spending breakdown");
        breakdown.setFont(new Font("Segoe UI", Font.BOLD, 20));
        breakdown.setForeground(Color.BLACK);
        breakdown.setBounds(20, 10, 400, 40);

        JLabel recent_transactions = new JLabel("Recent Transactions");
        recent_transactions.setFont(new Font("Segoe UI", Font.BOLD, 20));
        recent_transactions.setForeground(Color.BLACK);
        recent_transactions.setBounds(20, 10, 260, 40);

        JLabel line_1 = new JLabel("──────────────────────────────────────────────────────────────────────────────────────────────────");
        line_1.setForeground(Color.GRAY);
        line_1.setBounds(20, 20, 700, 65);

        JLabel line_2 = new JLabel("─────────────────────────────────────────");
        line_2.setForeground(Color.GRAY);
        line_2.setBounds(20, 20, 400, 65);

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
                    1, 1, 
                    getWidth() -3, getHeight() -3, 
                    25, 25
                );

                g6.setColor(Color.decode("#CBD5E1"));
                g6.setStroke(new java.awt.BasicStroke(2));
                g6.drawRoundRect(
                    1, 1,
                    getWidth() -3, getHeight() -3,
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
                    1, 1, 
                    getWidth(), getHeight(),
                    25, 25
                );

                g9.setColor(Color.decode("#CBD5E1"));
                g9.setStroke(new java.awt.BasicStroke(2));
                g9.drawRoundRect(
                    1, 1, 
                    getWidth() -3, getHeight() -3, 
                    25, 25
                );
            g9.dispose();
            }
        };
        chart.setBounds(35, 400, 670, 340);
        chart.setBackground(Color.decode("#ffffff"));
        chart.setLayout(null);
        chart.setOpaque(false);

        recents.setBounds(730, 400, 300, 340);
        recents.setBackground(Color.decode("#ffffff"));
        recents.setLayout(null);
        recents.setOpaque(false);


        JPanel transaction_list = new JPanel();
        transaction_list.setLayout(null);
        transaction_list.setOpaque(false);

        //scroll
        JScrollPane transaction_scroll = new JScrollPane(transaction_list);
        transaction_scroll.setBounds(15, 60, 270, 235);
        transaction_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        transaction_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        transaction_scroll.setBorder(null);
        transaction_scroll.setOpaque(false);
        transaction_scroll.getViewport().setOpaque(false);
        transaction_scroll.getVerticalScrollBar().setUnitIncrement(16);

        JScrollBar vertical_scroll = transaction_scroll.getVerticalScrollBar();
        vertical_scroll.setPreferredSize(new Dimension(10, 0));
        vertical_scroll.setUI(new BasicScrollBarUI() {
            @Override 
            protected void configureScrollBarColors() {
                this.thumbColor = Color.decode("#4F46E5");
                this.trackColor = Color.decode("#EEF2FF");
            }

            @Override 
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                return button;
            }

            @Override 
            protected void paintThumb(java.awt.Graphics g, javax.swing.JComponent c, java.awt.Rectangle thumbBounds) {
                java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();

                g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#4F46E5"));
                g2.fillRoundRect(
                    thumbBounds.x + 1,
                    thumbBounds.y, 
                    Math.max(0, thumbBounds.width -2), 
                    thumbBounds.height, 
                    10, 10
                );
                g2.dispose();
            }
        });
 
        recents.add(transaction_scroll);

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
        transaction_process.setBackground(Color.decode("#4F46E5"));
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
        transaction_form.getContentPane().setBackground(Color.decode("#F8FAFC"));

        JLabel newtransaction_text = new JLabel("New transaction");
        newtransaction_text.setBounds(40, 30, 400, 30);
        newtransaction_text.setFont(new Font("Segoe UI", Font.BOLD, 20));
        newtransaction_text.setForeground(Color.decode("#0F172A"));
        transaction_form.add(newtransaction_text);

        JLabel transaction_type = new JLabel("Type of transaction");
        transaction_type.setBounds(40, 65, 400, 30);
        transaction_type.setFont(new Font ("Segoe UI", Font.PLAIN, 16));
        transaction_type.setForeground(Color.decode("#0F172A"));
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
                    0, 0, Color.decode("#F1F5F9"), 
                    getWidth(), getHeight(), Color.decode("#F1F5F9")
                );
                g16.setPaint(gradient_expenses);

                g16.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(),
                    25, 25
                );

                g16.setColor(Color.decode("#CBD5E1"));
                g16.setStroke(new java.awt.BasicStroke(2));
                g16.drawRoundRect(
                    1, 1, 
                    getWidth() -3, getHeight() -3, 
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
                g18.dispose();  
                super.paintComponent(g17);       

                if ("INCOME".equals(selected_type[0])) {
                    java.awt.Graphics2D border = (java.awt.Graphics2D) g17.create();
                    border.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    border.setColor(Color.BLUE);
                    border.setStroke(new java.awt.BasicStroke(3));

                    border.drawRoundRect(2, 2, 
                        getWidth() -5, 
                        getHeight() -5, 
                        25, 25
                    );
                    border.dispose();
                }
                       
            }
        };
        income_record.setFont(new Font("Segoe UI", Font.BOLD, 15));
        income_record.setBackground(Color.decode("#15803D"));
        income_record.setForeground(Color.WHITE);
        income_record.setFocusPainted(false);
        income_record.setBounds(10, 80, 490, 45);
        income_record.setContentAreaFilled(false);
        income_record.setOpaque(false);
        income_record.setBorderPainted(false);

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

                g20.dispose();
                super.paintComponent(g19);

                if ("EXPENSE".equals(selected_type[0]))
                {
                    java.awt.Graphics2D border = (java.awt.Graphics2D) g19.create();
                    border.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
                    border.setColor(Color.BLUE);
                    border.setStroke(new java.awt.BasicStroke(3));

                    border.drawRoundRect(2, 2, 
                        getWidth() -5, 
                        getHeight() -5, 
                        25, 25
                    );
                    border.dispose();
                }
            }
        };
        expense_record.setFont(new Font("Segoe UI" , Font.BOLD, 15));
        expense_record.setBackground(Color.decode("#B91C1C"));
        expense_record.setForeground(Color.WHITE);
        expense_record.setFocusPainted(false);
        expense_record.setBounds(510, 80, 490, 45);
        expense_record.setContentAreaFilled(false);
        expense_record.setBorderPainted(false);

        expense_record.addActionListener(e -> {
            selected_type[0] = "EXPENSE";
            System.out.println("Selected: " + selected_type[0]);
            income_record.repaint();
            expense_record.repaint();

            updateLegend.run();
        });

        income_record.addActionListener(e -> {
            selected_type[0] = "INCOME";
            System.out.println("Selected: " + selected_type[0]);

            income_record.repaint();
            expense_record.repaint();

            updateLegend.run();
        });

        transactionform_border.add(expense_record);

        // 8.1 INPUTS
        JLabel reminders = new JLabel("Make sure to choose type of transaction first before saving");
        reminders.setFont(new Font("Segoe UI", Font.BOLD, 15));
        reminders.setForeground(Color.GRAY);
        reminders.setBounds(275, 500, 450, 30);
        transactionform_border.add(reminders); 

        JLabel description_label = new JLabel("Description");
        description_label.setForeground(Color.decode("#0F172A"));
        description_label.setBounds(25, 145, 250, 30);
        transactionform_border.add(description_label);

        JLabel amount_label = new JLabel("Amount");
        amount_label.setForeground(Color.decode("#0F172A"));
        amount_label.setBounds(25, 230, 250, 30);
        transactionform_border.add(amount_label);

        JLabel category_label = new JLabel("Category");
        category_label.setForeground(Color.decode("#0F172A"));
        category_label.setBounds(25, 315, 250, 30);
        transactionform_border.add(category_label);

        // description
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
        description_input.setBackground(Color.decode("#E2E8F0"));
        description_input.setForeground(Color.decode("#0F172A"));
        description_input.setOpaque(false);
        description_input.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)
        ); 

        // ------------- cateogyr
        JTextField CategorySet = new JTextField() {
            @Override 
            protected void paintComponent(java.awt.Graphics g25) {
                java.awt.Graphics2D g26 = (java.awt.Graphics2D) g25.create();
                g26.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );
                
                g26.setColor(getBackground());
                g26.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(),
                    25, 25
                );
                g26.dispose();
                super.paintComponent(g25);
            }
        };

        
        CategorySet.setFont(new Font("Segoe UI" , Font.PLAIN, 16));
        CategorySet.setBounds(20, 345, 970, 40);
        CategorySet.setBackground(Color.decode("#E2E8F0"));
        CategorySet.setForeground(Color.decode("#0F172A"));
        CategorySet.setOpaque(false);
        CategorySet.setEditable(false);
        CategorySet.setText("");
        CategorySet.setBorder(
            javax.swing.BorderFactory.createEmptyBorder (0, 15, 0 , 15)
        );

        JDialog category_dialog = new JDialog(
            transaction_form, 
            "Select Category" , 
            true
        );

        category_dialog.setSize(550, 450);
        category_dialog.setLocationRelativeTo(transaction_form);
        JPanel category_panel = new JPanel(
            new GridLayout(4, 2, 15, 15)
        ); 
        category_panel.setBackground(Color.decode("#F8FAFC"));
        category_panel.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        String[] expense_categories = { 
            "Food" , 
            "Transportation" , 
            "Shopping" , 
            "Bills" , 
            "Entertainment" , 
            "Health" , 
            "Education" , 
            "Others"
        };

        String[] income_categories = {
            "Salary" ,
            "Allowance" , 
            "Business" , 
            "Freelance" , 
            "Gifts" ,
            "Investments" , 
            "Refunds" , 
            "Others"  
        };

        Runnable updateCategories = () -> {
            category_panel.removeAll();
            String[] current_categories;

            if (selected_type[0].equals("INCOME")) {
                current_categories = income_categories;
            }
            else
            {
                current_categories = expense_categories;
            }

            for(String categoryName : current_categories) {
                JButton categoryButton = new JButton(categoryName) {
                    @Override 
                    protected void paintComponent(java.awt.Graphics g) {
                        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();

                        g2.setRenderingHint(
                            java.awt.RenderingHints.KEY_ANTIALIASING, 
                            java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        GradientPaint gradient_category = new GradientPaint (
                            0, 0, Color.decode("#E8E9FF"), 
                            getWidth(), getHeight(), Color.decode("#E0E7FF")
                        );

                        g2.setPaint(gradient_category);

                        g2.fillRoundRect(
                            1, 1, 
                            getWidth() -3, 
                            getHeight() -3, 
                            25, 25
                        );

                        g2.setColor(Color.decode("#818CF8"));
                        g2.setStroke(new java.awt.BasicStroke(1));

                        g2.drawRoundRect(
                            1, 1, 
                            getWidth() -3, 
                            getHeight() -3, 
                            25, 25
                        );

                        g2.dispose();
                        super.paintComponent(g);
                    }
                };
                categoryButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
                categoryButton.setForeground(Color.decode("#0F172A"));
                categoryButton.setContentAreaFilled(false);
                categoryButton.setOpaque(false);
                categoryButton.setBorderPainted(false);
                categoryButton.setFocusPainted(false);

                categoryButton.addActionListener(e -> {
                    CategorySet.setText(categoryName);
                    System.out.println("Category: " + categoryName);
                    category_dialog.setVisible(false);
                });
                category_panel.add(categoryButton);
            }

            category_panel.revalidate();
            category_panel.repaint();
        };
        category_dialog.setContentPane(category_panel);
        CategorySet.addMouseListener(new MouseAdapter() {
            @Override 
            public void mouseClicked(MouseEvent e) {
                if (selected_type[0].equals("NONE")) {
                    JOptionPane.showMessageDialog(
                        transaction_form, 
                        "Please select income or expense" , 
                        "Transaction Type required",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                updateCategories.run();

                category_dialog.setLocationRelativeTo(transaction_form);
                category_dialog.setVisible(true);
            }
        });

        income_record.addActionListener(e -> {
            CategorySet.setText("");
        });

        expense_record.addActionListener(e -> {
            CategorySet.setText("");
        });
        

         // ----- amount
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
        AmountSet.setBackground(Color.decode("#E2E8F0"));
        AmountSet.setForeground(Color.decode("#0F172A"));
        AmountSet.setOpaque(false);
        AmountSet.setBorder(
            javax.swing.BorderFactory.createEmptyBorder(0, 15, 0, 15)
        );

        // --- save transaction button
        JButton save_transaction = new JButton("Save transaction") {
            @Override 
            protected void paintComponent(java.awt.Graphics g27) {
                java.awt.Graphics2D g28 = (java.awt.Graphics2D) g27.create();
                g28.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );
                g28.setColor(getBackground());
                g28.fillRoundRect(
                    0, 0,
                    getWidth(), getHeight(),
                    25, 25
                );
                super.paintComponent(g27);
            }
        };

        save_transaction.setFont (new Font("Segoe UI", Font.BOLD, 16));
        save_transaction.setBackground(Color.decode("#4F46E5"));
        save_transaction.setForeground(Color.white);
        save_transaction.setFocusPainted(false);
        save_transaction.setBounds(20, 410, 970, 80);
        save_transaction.setContentAreaFilled(false);
        save_transaction.setOpaque(false);
        save_transaction.setBorderPainted(false);
        // REFRESH DASHBOARD
        final Runnable[] refreshDashboard = new Runnable[1];
        final Runnable[] updateChartVisibility = new Runnable[1];
        final Runnable[] refreshLegend = new Runnable[1];

        refreshDashboard[0] = () -> {
            // UPDATE TOTAL BALANCE
            amount.setText(
                String.format("₱%,.2f", manager.getBalance())
            );

            // UPDATE INCOME
            income_amount.setText(
                String.format("₱%,.2f", manager.getTotalIncome())
            );

            // UPDATE EXPENSES
            expenses_amount.setText(
                String.format("₱%,.2f", manager.getTotalExpenses())
            );

            // UPDATE RECENT TRANSACTIONS
            RecentTransactions.updateList(
                transaction_list,
                manager,
                refreshDashboard[0]
            );

            if (refreshLegend[0] != null) {
                refreshLegend[0].run();
            }

            if (updateChartVisibility[0] != null) {
                updateChartVisibility[0].run();
            }
        };
        refreshLegend[0] = updateLegend;

        save_transaction.addActionListener(e -> {
            recent_transactions_statement.setText("");

            String description = description_input.getText();
            String amountstr = AmountSet.getText();
            String category = CategorySet.getText();
            

            System.out.println("Description: " + description);
            System.out.println("Amount: " + amountstr);
            System.out.println("Category: " + category);

            if (description.trim().isEmpty() || 
                amountstr.trim().isEmpty() || 
                category.trim().isEmpty()
            )
            {
                JOptionPane.showMessageDialog(
                    transaction_form, 
                    "Please fill in the blanks" , 
                    "Input error", 
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (amountstr.trim().matches(".*[a-zA-Z]+.*")) {
                JOptionPane.showMessageDialog(
                    transaction_form, 
                    "Please enter a valid amount", 
                    "Input error", 
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            if (selected_type[0].equals("NONE")) {
                JOptionPane.showMessageDialog(transaction_form, 
                    "INCOME AT EXPENSE LANG NGANI!",
                    "Transaction Type Required", 
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            // VALIDATE AMOUNT
                double money;

                try {
                    money = Transaction_manager.validateAmount(amountstr);
                }
                catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(
                        transaction_form,
                        ex.getMessage(),
                        "Invalid Amount",
                        JOptionPane.ERROR_MESSAGE
                    );
                    
                    return;
                }

                // CREATE TRANSACTION OBJECT
                Transaction newTransaction = new Transaction(
                    selected_type[0],
                    description.trim(),
                    category.trim(),
                    money
                );

            // SAVE TRANSACTION IN ARRAYLIST
            manager.addTransaction(newTransaction);

            refreshDashboard[0].run();

            System.out.println("Transaction SAVED!!!");
            System.out.println("Total Income: " + manager.getTotalIncome());
            System.out.println("Total Expenses: " + manager.getTotalExpenses());
            System.out.println("Balance: " + manager.getBalance());

            description_input.setText("");
            AmountSet.setText("");
            CategorySet.setText("");

            selected_type[0] = "NONE";
            transaction_form.setVisible(false);
        });

            JButton income_chart = new JButton("INCOME") {
            @Override 
            protected void paintComponent(java.awt.Graphics g45) {
                java.awt.Graphics2D g46 = (java.awt.Graphics2D) g45.create();

                g46.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );

                g46.setColor(getBackground());
                g46.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(), 
                    25, 25
                );
                if ("INCOME".equals(selected_chart[0])) {
                    g46.setColor(Color.BLUE);
                    g46.setStroke(new java.awt.BasicStroke(3));

                    g46.drawRoundRect(
                        2, 2, 
                        getWidth() -5, 
                        getHeight() -5,
                        25, 25
                    );
                }
                    g46.dispose();
                    super.paintComponent(g45);
                }
            };

            income_chart.setFont(new Font("Segoe UI" , Font.PLAIN, 15));
            income_chart.setBackground(Color.LIGHT_GRAY);
            income_chart.setBorderPainted(false);
            income_chart.setContentAreaFilled(false);
            income_chart.setFocusPainted(false);
            income_chart.setBounds(20, 65, 130, 25);

            chart.add(income_chart);
            income_chart.setVisible(false);

        JButton expenses_chart = new JButton("EXPENSES") {
            @Override 
            protected void paintComponent(java.awt.Graphics g47) {
                java.awt.Graphics2D g48 = (java.awt.Graphics2D) g47.create();

                g48.setRenderingHint(
                    java.awt.RenderingHints.KEY_ANTIALIASING, 
                    java.awt.RenderingHints.VALUE_ANTIALIAS_ON
                );

                g48.setColor(getBackground());
                g48.fillRoundRect(
                    0, 0, 
                    getWidth(), getHeight(), 
                    25, 25
                );

                if ("EXPENSE".equals(selected_chart[0])) {
                    g48.setColor(Color.BLUE);
                    g48.setStroke(new java.awt.BasicStroke(3));

                    g48.drawRoundRect(
                        2, 2, 
                        getWidth() -5, 
                        getHeight() -5,
                        25, 25
                    );
                }

                    g48.dispose();
                    super.paintComponent(g47);
                }  
            };

            expenses_chart.setFont(new Font("Segoe UI" , Font.PLAIN, 15));
            expenses_chart.setBackground(Color.LIGHT_GRAY);
            expenses_chart.setBorderPainted(false);
            expenses_chart.setContentAreaFilled(false);
            expenses_chart.setFocusPainted(false);
            expenses_chart.setBounds(170, 65, 130, 25);

            chart.add(expenses_chart);
            expenses_chart.setVisible(false);
            updateChartVisibility[0] = () -> {
                boolean has_transaction = manager.getTotalIncome() > 0 || manager.getTotalExpenses() > 0;

                income_chart.setVisible(has_transaction);
                expenses_chart.setVisible(has_transaction);

                if (has_transaction) {
                    breakdown_statement.setText("");
                }
                else
                {
                    breakdown_statement.setText("No data yet");
                }
                spending_chart.repaint();
            };

            expenses_chart.addActionListener(e -> {
                selected_chart[0] = "EXPENSE";
                breakdown.setText("Total spending breakdown");

                expenses_card.repaint();
                spending_chart.repaint();
                income_chart.repaint();

                updateLegend.run();
            });

            income_chart.addActionListener(e -> {
                selected_chart[0] = "INCOME";
                breakdown.setText("Total income breakdown");

                expenses_chart.repaint();
                spending_chart.repaint();
                income_chart.repaint();

                updateLegend.run();
            });

            transaction_form.setVisible(false);


        JButton add_transaction_back = new JButton("← Back") {
            @Override 
            protected void paintComponent(java.awt.Graphics g30) {
                java.awt.Graphics2D g31 = (java.awt.Graphics2D) g30.create();

                g31.setRenderingHint (
                java.awt.RenderingHints.KEY_ANTIALIASING, 
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON
            );

            g31.setColor(getBackground());
            g31.fillRoundRect(
                0, 0, 
                getWidth(), getHeight(), 
                25, 25
            );
            g31.dispose();
            super.paintComponent(g30);
            }
        };
        add_transaction_back.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add_transaction_back.setBackground(Color.decode("#4F46E5"));
        add_transaction_back.setForeground(Color.WHITE);
        add_transaction_back.setFocusPainted(false);
        add_transaction_back.setBounds(900, 25, 100, 40);
        add_transaction_back.setContentAreaFilled(false);
        add_transaction_back.setOpaque(false);
        add_transaction_back.setBorderPainted(false);
        add_transaction_back.addActionListener(e -> {
            transaction_form.setVisible(false);           
        });

        transactionform_border.add(add_transaction_back);

        transactionform_border.add(description_input);
        transactionform_border.add(AmountSet);
        transactionform_border.add(CategorySet);
        transactionform_border.add(save_transaction);

        // 8.2 donut choices


        JButton view_history = new JButton("View all transactions");
        view_history.setFont(new Font ("Segoe UI" , Font.BOLD, 12));
        view_history.setBackground(Color.decode("#EEF2FF"));
        view_history.setForeground(Color.decode("#4F46E5"));
        view_history.setBorderPainted(false);
        view_history.setFocusPainted(false);
        view_history.setBounds(40, 305, 220, 25);
        view_history.addActionListener(e -> {
            Transaction_history.show_history(MAIN_INTERFACE, manager);
        });

        // 9. ADD COMPONENTS TO THEIR CONTAINERS


        box_card.add(total_balance);
        box_card.add(amount);
        box_card.add(available_balance);
        
        income_card.add(income);
        income_card.add(income_amount);

        expenses_card.add(expenses);
        expenses_card.add(expenses_amount);

        chart.add(breakdown);
        chart.add(line_1);
        chart.add(breakdown_statement);
        chart.add(spending_chart);
        chart.add(category_legend);
        chart.setLayout(null);
        
        recents.add(recent_transactions_statement);
        recents.add(recent_transactions);
        recents.add(line_2);
        recents.add(view_history);

        MAIN_INTERFACE.add(MyWallet);
        MAIN_INTERFACE.add(box_card);
        MAIN_INTERFACE.add(income_card);
        MAIN_INTERFACE.add(expenses_card);
        MAIN_INTERFACE.add(chart);
        MAIN_INTERFACE.add(recents);
        MAIN_INTERFACE.add(transaction_process);

        // 10. display winodw
        MAIN_INTERFACE.setVisible(true);

        System.out.println("SUCCESSFUL EXECUTION");
        System.out.println("----------------------");
        System.out.println("--- RECENT ACTIONS --- ");
        System.out.println("----------------------");
    }
}

