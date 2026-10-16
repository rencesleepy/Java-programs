import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFirstGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel label = new JLabel("What is your programming language? ");
        
        JTextField input = new JTextField();
        JTextField name_input = new JTextField();
        JLabel name_label = new JLabel("Enter your name: ");

        JButton button = new JButton("Submit");
        JLabel result = new JLabel(" ");
        JButton clear = new JButton("Clear");

        JLabel fill = new JLabel();
        JLabel no_clear = new JLabel();

        frame.setTitle("Testing my GUI");
        frame.setSize(430, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        label.setBounds(80, 105, 300, 30); 
        input.setBounds(100, 135, 200, 30); 
        name_input.setBounds(100, 65, 200, 30 ); 
        button.setBounds(70, 180, 120, 40); 
        result.setBounds(35, 220, 400, 30); 
        clear.setBounds(200, 180, 120, 40); 
        name_label.setBounds(80, 35, 300, 30);
        fill.setBounds(160, 220, 120, 40);
        no_clear.setBounds(130, 220, 140, 40);

        label.setForeground(Color.WHITE);
        name_label.setForeground(Color.WHITE);
        fill.setForeground(Color.RED);
        result.setForeground(Color.WHITE);
        clear.setForeground(Color.WHITE);
        no_clear.setForeground(Color.WHITE);
        
        button.setBackground(Color.GREEN);
        clear.setBackground(Color.RED);

        frame.add(label);
        frame.add(input);
        frame.add(button);
        frame.add(result);
        frame.add(clear);
        frame.add(name_input);
        frame.add(name_label);
        frame.add(fill);
        frame.add(no_clear);

        button.addActionListener(e -> {
            String name = input.getText();
            String myname = name_input.getText();
            if (name.isEmpty() || myname.isEmpty())
            {
                result.setText("");
                fill.setText("Please fill in!");
                no_clear.setText("");
            }
            else
            {
                result.setText("Hello! " + myname + " Your favorite programming languege is " + name + "!");
                fill.setText("");
                no_clear.setText("");
            }
        });

        clear.addActionListener(e -> {
            String name = input.getText();
            String myname = input.getText();
            if (name.isEmpty() || myname.isEmpty())
            {
                no_clear.setText("Nothing can be cleared");
                fill.setText("");
            }
            else
            {
            input.setText("");
            name_input.setText("");
            result.setText("");
            fill.setText("");
            }
        });

        frame.setVisible(true);

    
    }
}





