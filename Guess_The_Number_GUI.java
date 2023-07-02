import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Guess_The_Number_GUI extends JFrame {
    private JPanel panelMain;
    private JTextField guesses_num;
    private JButton btnClick;

    private int randomNum;
    private int count;

    public Guess_The_Number_GUI() {
        btnClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guessed_num = Integer.parseInt(guesses_num.getText());
                int diff = randomNum - guessed_num;

                if (diff > 0) {
                    JOptionPane.showMessageDialog(btnClick, "Entered number is smaller");
                    if (diff >= 50) {
                        JOptionPane.showMessageDialog(btnClick, "Hint: Entered number is too low.\nEnter a number which should be greater than the previous one");
                    } else if (diff >= 25) {
                        JOptionPane.showMessageDialog(btnClick, "Hint: Entered number is low.\nEnter a number which should be greater than the previous one");
                    } else if (diff >= 10) {
                        JOptionPane.showMessageDialog(btnClick, "Hint: Came closer but still low.\nEnter a number which should be greater than the previous one");
                    } else {
                        JOptionPane.showMessageDialog(btnClick, "Hint: You are so close.\nEnter a number which should be greater than the previous one");
                    }
                } else if (diff < 0) {
                    JOptionPane.showMessageDialog(btnClick, "Entered number is greater");
                    diff = Math.abs(diff);
                    if (diff > 50) {
                        JOptionPane.showMessageDialog(btnClick, "Hint: Entered number is too big.\nEnter a number which should be smaller than the previous one");
                    } else if (diff >= 25) {
                        JOptionPane.showMessageDialog(btnClick, "Hint: Entered number is big.\nEnter a number which should be smaller than the previous one");
                    } else if (diff >= 10) {
                        JOptionPane.showMessageDialog(btnClick, "Hint: Came closer but still big.\nEnter a number which should be smaller than the previous one");
                    } else {
                        JOptionPane.showMessageDialog(btnClick, "Hint: You are so close.\nEnter a number which should be smaller than the previous one");
                    }
                } else {
                    JOptionPane.showMessageDialog(btnClick, "You guessed the right number!\nCongratulations!!!!");
                    JOptionPane.showMessageDialog(btnClick, "Your score is : "+(count-1)*100);
                    resetGame();
                }

                count--;
                if (count == 0) {
                    JOptionPane.showMessageDialog(btnClick, "No chances are left.\nBetter luck next time");
                    resetGame();
                } else {
                    JOptionPane.showMessageDialog(btnClick, "Chances left: " + count);
                }
            }
        });
    }

    private void resetGame() {
        guesses_num.setText("");
        randomNum = generateRandomNumber();
        count = 10;
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    public static void main(String[] args) {
        Guess_The_Number_GUI game = new Guess_The_Number_GUI();
        game.setContentPane(game.panelMain);
        game.setTitle("Guess the Number Game");
        game.setSize(300, 400);
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.resetGame();
    }
}
