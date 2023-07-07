import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Task_2_Codesoft extends JFrame {
    private JPanel panelmain;
    private JTextArea textArea1;
    private JButton button1;

    public Task_2_Codesoft() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String paragraph = textArea1.getText();
                String[] words = paragraph.split("\\s+");

                // Count word frequency
                Map<String, Integer> wordFrequency = new HashMap<>();
                for (String word : words) {
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }

                // Get distinct words
                String[] distinctWords = wordFrequency.keySet().toArray(new String[0]);

                // Prompt number of unique words
                int uniqueWordCount = distinctWords.length;
                JOptionPane.showMessageDialog(Task_2_Codesoft.this,
                        "Number of Unique Words: " + uniqueWordCount);

                // Display distinct words and their frequency
                StringBuilder result = new StringBuilder();
                for (String word : distinctWords) {
                    int frequency = wordFrequency.get(word);
                    result.append(word).append(": ").append(frequency).append("\n");
                }

                JOptionPane.showMessageDialog(Task_2_Codesoft.this, result.toString());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Task_2_Codesoft frame = new Task_2_Codesoft();
            frame.setContentPane(frame.panelmain);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
