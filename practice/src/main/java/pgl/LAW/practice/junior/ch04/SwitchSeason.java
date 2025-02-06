package pgl.LAW.practice.junior.ch04;

import java.util.Scanner;

public class SwitchSeason {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("please input the season you are: ");
        int month = input.nextInt();
        switch (month) {
            case 3:
            case 4:
            case 5: System.out.println("Spring"); break;
            case 6:
            case 7:
            case 8: System.out.println("Summer"); break;
            case 9:
            case 10:
            case 11: System.out.println("Altumn"); break;
            case 12:
            case 1:
            case 2: System.out.println("Winter"); break;
            default: System.out.println("not exist");
        }
    }
}
