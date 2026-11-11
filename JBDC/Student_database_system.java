
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

    private static final String URL =
        "jdbc:oracle:thin:@//localhost:1521/XEPDB1";

    private static final String USER = "SQL_PRACTICE";

    private static final String PASSWORD =
        System.getenv("ORACLE_DB_PASSWORD");


    // VALIDATE STUDENT INFORMATION
    public static boolean validateStudent(
            String idText,
            String name,
            String program) {

        // Check if ID is a number
        int id;

        try {
            id = Integer.parseInt(idText.trim());

            if (id <= 0) {
                JOptionPane.showMessageDialog(
                    null,
                    "Student ID must be greater than zero."
                );
                return false;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                null,
                "Student ID must be a number."
            );

            return false;
        }

        // Check student name
        if (name.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                null,
                "Student name cannot be empty."
            );

            return false;
        }

        // Check program
        if (program.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                null,
                "Program cannot be empty."
            );

            return false;
        }

        return true;
    }


    // SEARCH STUDENT
    public static String findStudent(int id) {

        String sql =
            "SELECT ID, NAME, PROGRAM " +
            "FROM M8_STUDENTS WHERE ID = ?";

        try (
            Connection conn =
                DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement pstmt =
                conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    return "Student ID: " + rs.getInt("ID")
                        + "\nStudent Name: " + rs.getString("NAME")
                        + "\nProgram: " + rs.getString("PROGRAM");
                }

                return "Student not found.";
            }

        } catch (SQLException e) {

            return "Database error: " + e.getMessage();
        }
    }


    // ADD STUDENT
    public static boolean addStudent(
            int id,
            String name,
            String program) {

        String sql =
            "INSERT INTO M8_STUDENTS (ID, NAME, PROGRAM) " +
            "VALUES (?, ?, ?)";

        try (
            Connection conn =
                DriverManager.getConnection(URL, USER, PASSWORD);

            PreparedStatement pstmt =
                conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, id);
            pstmt.setString(2, name.trim());
            pstmt.setString(3, program.trim());

            int rows = pstmt.executeUpdate();

            return rows == 1;

        } catch (SQLException e) {

            // Oracle error code 1 = duplicate primary key
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


    // CREATE GUI
    public static void createGUI() {

        JFrame frame =
            new JFrame("Student Database System");

        frame.setSize(700, 400);

        frame.setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE
        );

        frame.setLocationRelativeTo(null);

        JPanel panel =
            new JPanel(new GridLayout(4, 2, 10, 10));

        // Student ID
        JLabel idLabel =
            new JLabel("Student ID:");

        JTextField idField =
            new JTextField();

        // Student Name
        JLabel nameLabel =
            new JLabel("Student Name:");

        JTextField nameField =
            new JTextField();

        // Program
        JLabel programLabel =
            new JLabel("Program:");

        JTextField programField =
            new JTextField();


        // ADD BUTTON
        JButton addButton =
            new JButton("Add Student");

        addButton.addActionListener(e -> {

            String idText = idField.getText();
            String name = nameField.getText();
            String program = programField.getText();

            if (validateStudent(idText, name, program)) {

                int id =
                    Integer.parseInt(idText.trim());

                boolean saved =
                    addStudent(id, name, program);

                if (saved) {

                    JOptionPane.showMessageDialog(
                        frame,
                        "Student saved successfully!"
                    );

                    // Clear fields
                    idField.setText("");
                    nameField.setText("");
                    programField.setText("");
                }
            }
        });


        // SEARCH BUTTON
        JButton searchButton =
            new JButton("Search");

        searchButton.addActionListener(e -> {

            String idText = idField.getText();

            // Validate ID
            if (idText.trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Please enter a Student ID."
                );

                return;
            }

            try {

                int id =
                    Integer.parseInt(idText.trim());

                if (id <= 0) {

                    JOptionPane.showMessageDialog(
                        frame,
                        "Student ID must be greater than zero."
                    );

                    return;
                }

                // Find student in database
                String result = findStudent(id);

                JOptionPane.showMessageDialog(
                    frame,
                    result,
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE
                );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                    frame,
                    "Student ID must be a number."
                );
            }
        });


        // CLEAR BUTTON
        JButton clearButton =
            new JButton("Clear");

        clearButton.addActionListener(e -> {

            idField.setText("");
            nameField.setText("");
            programField.setText("");
        });


        // EXIT BUTTON
        JButton exitButton =
            new JButton("Exit");

        exitButton.addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to exit?",
                "Exit System",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });


        // ADD COMPONENTS TO PANEL
        panel.add(idLabel);
        panel.add(idField);

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(programLabel);
        panel.add(programField);

        panel.add(addButton);
        panel.add(searchButton);

        panel.add(clearButton);
        panel.add(exitButton);

        frame.add(panel);

        frame.setVisible(true);
    }


    // MAIN METHOD
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            createGUI();
        });
    }
}
