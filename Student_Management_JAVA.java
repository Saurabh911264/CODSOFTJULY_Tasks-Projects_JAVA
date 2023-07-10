import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Student_Management_JAVA extends JFrame {
    private JTextField rollNoField;
    private JTextField nameField;
    private JTextField classField;
    private JTextField gradesField;
    private JTextField phoneNumberField;
    private JTextField ageField;
    private JComboBox<String> typeComboBox;

    private Connection connection;
    private Statement statement;
    private JButton submitButton;
    private JPanel panelMain;

    public Student_Management_JAVA() {
        // Create a database connection
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rollNo = rollNoField.getText();
                String name = nameField.getText();
                String studentClass = classField.getText();
                String grades = gradesField.getText();
                String phoneNumber = phoneNumberField.getText();
                String age = ageField.getText();
                String type = (String) typeComboBox.getSelectedItem();

                if (type != null && type.equals("Resister")) {
                    registerStudent(rollNo, name, studentClass, grades, phoneNumber, age);
                } else if (type != null && type.equals("Modify")) {
                    modifyStudent(rollNo, name, studentClass, grades, phoneNumber, age);
                } else if (type != null && type.equals("Remove")) {
                    removeStudent(rollNo);
                } else if (type != null && type.equals("Show")) {
                    showStudent(rollNo);
                }

            }
        });

        // Set JFrame properties
        setTitle("Student Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void registerStudent(String rollNo, String name, String studentClass, String grades, String phoneNumber, String age) {
        try {
            String query = "INSERT INTO Student VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rollNo);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, studentClass);
            preparedStatement.setString(4, grades);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.setString(6, age);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student registered successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to register student.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void modifyStudent(String rollNo, String name, String studentClass, String grades, String phoneNumber, String age) {
        try {
            String query = "UPDATE Student SET name = ?, class = ?, grade = ?, phone_number = ?, age = ? WHERE roll_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, studentClass);
            preparedStatement.setString(3, grades);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setString(5, age);
            preparedStatement.setString(6, rollNo);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student modified successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to modify student.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeStudent(String rollNo) {
        try {
            String query = "DELETE FROM Student WHERE roll_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rollNo);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student removed successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove student.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showStudent(String rollNo) {
        try {
            String query = "SELECT * FROM Student WHERE roll_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, rollNo);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String studentClass = resultSet.getString("class");
                String grades = resultSet.getString("grade");
                String phoneNumber = resultSet.getString("phone_number");
                String age = resultSet.getString("age");

                StringBuilder sb = new StringBuilder();
                sb.append("Name: ").append(name).append("\n");
                sb.append("Class: ").append(studentClass).append("\n");
                sb.append("Grades: ").append(grades).append("\n");
                sb.append("Phone Number: ").append(phoneNumber).append("\n");
                sb.append("Age: ").append(age).append("\n");

                JOptionPane.showMessageDialog(this, sb.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.");
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Student_Management_JAVA game=new Student_Management_JAVA();
                game.setContentPane(game.panelMain);
                game.setTitle("Student Management System");
                game.setSize(300, 400);
                game.setVisible(true);
                game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
