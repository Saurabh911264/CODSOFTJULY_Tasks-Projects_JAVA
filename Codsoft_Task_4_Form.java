import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Codsoft_Task_4_Form extends JFrame {
    private JComboBox<String> transactionComboBox;
    private JTextField accountNumberTextField;
    private JButton submitButton;
    private Connection connection;
    private Statement statement;
    private JPanel panelMain;
    public Codsoft_Task_4_Form() {
        setTitle("ATM Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Add ActionListener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberTextField.getText();
                String transactionType = (String) transactionComboBox.getSelectedItem();

                if (transactionType.equals("Check Balance")) {
                    checkBalance(accountNumber);
                } else {
                    String amountString = JOptionPane.showInputDialog(null, "Enter the amount:");
                    if (amountString != null && !amountString.isEmpty()) {
                        double amount = Double.parseDouble(amountString);
                        if (transactionType.equals("Withdraw")) {
                            performWithdraw(accountNumber, amount);
                        } else if (transactionType.equals("Deposit")) {
                            performDeposit(accountNumber, amount);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid amount");
                    }
                }
            }
        });

        // Establish database connection
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkBalance(String accountNumber) {
        try {
            String query = "SELECT acc_holder_name,balance FROM account WHERE account_number = " + accountNumber;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                String name = resultSet.getString("acc_holder_name");
                JOptionPane.showMessageDialog(this, "Account Holder : "+name+"\n"+"Account Balance: $" + balance+"\n");
            } else {
                JOptionPane.showMessageDialog(this, "Account not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void performWithdraw(String accountNumber, double amount) {
        try {
            String query = "SELECT acc_holder_name,balance FROM account WHERE account_number = " + accountNumber;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("acc_holder_name");
                double balance = resultSet.getDouble("balance");
                if (balance >= amount) {
                    balance -= amount;
                    updateBalance(accountNumber, balance);
                    JOptionPane.showMessageDialog(this, "Account Holder : "+name+"\n"+"Withdrawal successful. New balance: $" + balance);
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Account not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void performDeposit(String accountNumber, double amount) {
        try {
            String query = "SELECT acc_holder_name,balance FROM account WHERE account_number = " + accountNumber;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String name = resultSet.getString("acc_holder_name");
                double balance = resultSet.getDouble("balance");
                balance += amount;
                updateBalance(accountNumber, balance);
                JOptionPane.showMessageDialog(this, "Account Holder : "+name+"\n"+"Deposit successful. New balance: $" + balance);
            } else {
                JOptionPane.showMessageDialog(this, "Account not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateBalance(String accountNumber, double balance) {
        try {
            String query = "UPDATE account SET balance = " + balance + " WHERE account_number = " + accountNumber;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Codsoft_Task_4_Form atmInterface = new Codsoft_Task_4_Form();
                atmInterface.setContentPane(atmInterface.panelMain);
                atmInterface.setSize(300, 200);
                atmInterface.setVisible(true);
            }
        });
    }
}