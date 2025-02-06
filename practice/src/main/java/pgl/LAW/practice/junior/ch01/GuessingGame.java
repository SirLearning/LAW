package pgl.LAW.practice.junior.ch01;
import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main (String arg[]) {
        int game;
        int guess;
        int counter=0;
        Random randomNumbers = new Random();
        // generate a integer vary from 0 to 99
        game = 1+randomNumbers.nextInt(100);
        // get input data from a Scanner class object
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("please input the number you're guessing: ");
            guess = input.nextInt();
            counter++;
            if (guess==game)
                break;
            if (guess>game)
                System.out.println("you're guessing bigger");
            else
                System.out.println("you're guessing smaller");
        } while (guess!=game);
        System.out.println("after "+counter+" times counting, finally, you're right");
    }
}
