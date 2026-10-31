import java.awt.Color;
import java.awt.GradientPaint;
import javax.swing.JPanel;

public class RoundedPanel {
    public static void main(String[] args) {
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

    }
}
