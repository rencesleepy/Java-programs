import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/** Keeps fixed-position forms reachable on smaller or scaled Windows screens. */
public final class WindowSizing {
    private WindowSizing() {}

    public static Dimension fitSize(Dimension requested, Rectangle usable) {
        return new Dimension(Math.min(requested.width, Math.max(1, usable.width - 24)),
            Math.min(requested.height, Math.max(1, usable.height - 24)));
    }

    public static void fit(JFrame window, Dimension canvasSize) {
        window.setContentPane(scroll(window.getContentPane(), canvasSize));
        fitWindow(window, canvasSize);
    }

    public static void fit(JDialog window, Dimension canvasSize) {
        window.setContentPane(scroll(window.getContentPane(), canvasSize));
        fitWindow(window, canvasSize);
    }

    private static JScrollPane scroll(Container canvas, Dimension canvasSize) {
        canvas.setPreferredSize(canvasSize);
        JScrollPane scroll = new JScrollPane(canvas);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(24);
        scroll.getHorizontalScrollBar().setUnitIncrement(24);
        return scroll;
    }

    private static void fitWindow(Window window, Dimension canvasSize) {
        GraphicsConfiguration display = window.getGraphicsConfiguration();
        Rectangle usable = new Rectangle(display.getBounds());
        Insets desktop = Toolkit.getDefaultToolkit().getScreenInsets(display);
        usable.x += desktop.left;
        usable.y += desktop.top;
        usable.width -= desktop.left + desktop.right;
        usable.height -= desktop.top + desktop.bottom;
        window.pack();
        Insets frame = window.getInsets();
        Dimension requested = new Dimension(canvasSize.width + frame.left + frame.right + 20,
            canvasSize.height + frame.top + frame.bottom + 20);
        Dimension size = fitSize(requested, usable);
        window.setSize(size);
        window.setLocation(usable.x + (usable.width - size.width) / 2,
            usable.y + (usable.height - size.height) / 2);
    }
}
