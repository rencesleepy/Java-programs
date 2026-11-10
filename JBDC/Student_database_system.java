
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Student_database_system {

    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
    private static final String USER = "SQL_PRACTICE";
    private static final String PASSWORD = System.getenv("ORACLE_DB_PASSWORD");

    public static boolean validateStudent(
        String idText, 
        String name, 
        String program) {
    
        try {
            int id = Integer.parseInt(idText.trim());
            if (id <= 0) {
                JOptionPane.showMessageDialog(
                    null, 
                    "student id must be greater than zero"
                );
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(
                null, 
                "student id must be a number"
            ); 
            return false;
        }
        if (name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                null, 
                "student name cannot empty"
            );
            return false;
        }
        if (program.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                null, 
                "program cannot be empty"
            );
        }
        return true;
    }

    public static String findStudent(int id) {

    String sql = "SELECT ID, NAME, PROGRAM FROM M8_STUDENTS WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return "Student ID: " + rs.getInt("ID")
                        + "\nName: " + rs.getString("NAME")
                        + "\nProgram: " + rs.getString("PROGRAM");
                }

                return "Not found";
            }

        } catch (SQLException e) {
            return "Database error: " + e.getMessage();
        }
    }

    public static boolean addStudent(int id, String name, String program) {

    String sql = "INSERT INTO M8_STUDENTS (ID, NAME, PROGRAM) "
               + "VALUES (?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(
                URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, program);

        int rows = pstmt.executeUpdate();

        return rows == 1;

        } catch (SQLException e) {

            if (e.getErrorCode() == 1) {
                JOptionPane.showMessageDialog(
                    null,
                    "Student ID already exists!"
                );
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Database error: " + e.getMessage()
                );
            }

            return false;
        }
    }


    public static void createGUI() {

        JFrame frame = new JFrame("Student Database System");

        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Student Name:");
        JTextField nameField = new JTextField();

        JLabel programLabel = new JLabel("Program:");
        JTextField programField = new JTextField();

        JButton addButton = new JButton("Add student");
        addButton.addActionListener(e -> {
            String idText = idField.getText();
            String name = nameField.getText();
            String program = programField.getText();

            if (validateStudent(idText, name, program))
            {
                int id = Integer.parseInt(idText.trim());
                boolean saved = addStudent(id, name, program);
                if (saved) {
                    JOptionPane.showMessageDialog(
                        frame, 
                        "student saved successfully"
                    );
                    idField.setText("");
                    nameField.setText("");
                    programField.setText("");
                }

                System.out.println("SUCCESSFULL VALIDATION");
            }

        });
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            
     
