import java.util.Random;
import java.util.Scanner;
public class CodeSoft_Task_1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        System.out.println("------Let's start the game!------");
        System.out.println("------You will be given 10 chances to guess the number------ \n------Best of luck------ ");
        int choice, count, diff;
        int guessed_Num;
        System.out.println("Enter 1 to continue or press any number to exit");
        choice = input.nextInt();
        int random_num = random.nextInt(100) + 1;
        while (choice == 1) {
            System.out.println("------Let's start the game!------");
            System.out.println("------You will be given 10 chances to guess the number------ \n------Best of luck------ ");
            random_num = random.nextInt(100) + 1;
            count=10;
            while (count > 1) {
                System.out.println("Enter your number---->");
                guessed_Num = input.nextInt();
                diff = random_num - guessed_Num;
                if (diff > 0) {
                    System.out.println("Entered Number is smaller");
                    if (diff >= 50) {
                        System.out.println("Hint :Entered Number is too low");
                        System.out.println("Enter a number which should be greater than the previous one");
                    } else if (diff >= 25) {
                        System.out.println("Hint :Entered Number is low");
                        System.out.println("Enter a number which should be greater than the previous one");
                    } else if (diff >= 10 ) {
                        System.out.println("Hint : Came closer but still low");
                        System.out.println("Enter a number which should be greater than the previous one");
                    } else {
                        System.out.println("Hint : You are so close");
                        System.out.println("Enter a number which should be greater than the previous one");
                    }
                } else if (diff < 0) {
                    System.out.println("Entered Number is greater");
                    diff = Math.abs(diff);
                    if (diff > 50) {
                        System.out.println("Hint :Entered Number is too big");
                        System.out.println("Enter a number which should be smaller than the previous one");
                    } else if (diff >= 25) {
                        System.out.println("Hint :Entered Number is big");
                        System.out.println("Enter a number which should be smaller than the previous one");
                    } else if (diff >= 10) {
                        System.out.println("Hint : Came closer but still big");
                        System.out.println("Enter a number which should be smaller than the previous one");
                    } else{
                        System.out.println("Hint : You are so close");
                        System.out.println("Enter a number which should be smaller than the previous one");
                    }
                } else {
                    System.out.println("You guessed the right number! \ncongratulations!!!!");
                    break;
                }
                count--;
                if (count==0){
                    System.out.println("No chances are left \nBetter luck next time");
                    System.out.println("--------------------------------------");
                }
                else{
                    System.out.println("Chances left are : "+count);
                    System.out.println("--------------------------------------");
                }
            }
            if(count==0){
                System.out.println("Enter 1 to continue or press any number to exit");
                choice = input.nextInt();
            }
            else{
                System.out.println("You have done a great job!");
                System.out.println("Your score is : "+(count-1)*100);
                System.out.println("Enter 1 to continue or press any number to exit");
                choice = input.nextInt();
            }
        }
        System.out.println("The number was ---> " + random_num);
        System.out.println("Thanks for playing the game!!");
    }
}
