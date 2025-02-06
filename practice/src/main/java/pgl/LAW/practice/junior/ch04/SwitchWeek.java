package pgl.LAW.practice.junior.ch04;

import java.util.Scanner;

public class SwitchWeek {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("please input a integer between 1 and 7");
        int day = input.nextInt();
        switch (day) {
            case 7: System.out.println("Sunday");break;
            case 1: System.out.println("Monday");break;
            case 2: System.out.println("Tuesday");break;
            case 3: System.out.println("Wednesday");break;
            case 4: System.out.println("Thursday");break;
            case 5: System.out.println("Friday");break;
            case 6: System.out.println("Saturday");break;
            default: System.out.println("not in the effective region!");
        }
    }
}
