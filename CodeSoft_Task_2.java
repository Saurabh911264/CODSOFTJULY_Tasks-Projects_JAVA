import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CodeSoft_Task_2 {
    public static String[] getDistinctWords(String paragraph) {
        String[] words = paragraph.split("\\s+");
        Set<String> wordSet = new HashSet<>();
        for (String word : words) {
            wordSet.add(word);
        }
        String[] distinctWords = new String[wordSet.size()];
        int index = 0;
        for (String word : wordSet) {
            distinctWords[index++] = word;
        }
        return distinctWords;
    }

    public static void main(String[] args) {
        String paragraph;
        Scanner input = new Scanner(System.in);
        paragraph=input.nextLine();
        String[] distinctWords=getDistinctWords(paragraph);
        System.out.println(distinctWords.length);
        for(int i=0;i<distinctWords.length;i++){
            System.out.println(distinctWords[i]);
        }
    }
}
