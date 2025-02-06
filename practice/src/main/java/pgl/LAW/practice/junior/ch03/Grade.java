package pgl.LAW.practice.junior.ch03;

import java.util.Scanner;

public class Grade {
    public static void main(String args[]) {
        String name;
        int SID;
        float grade[] = new float[8];
        Scanner in = new Scanner(System.in);
        System.out.println("Input the SID and name:");
        SID = in.nextInt();
        name = in.next();
        System.out.println("Input 6 grades:");
        for (int i=0; i<=5; i++) {
            grade[i] = in.nextInt();
            grade[6] += grade[i];
        }
        grade[7] = grade[6]/6;
        System.out.println("SID: "+SID+"\tname: "+name);
        for (int i=0; i<=5; i++) {
            System.out.println("grade="+grade[i]);
        }
        System.out.println("Sum= "+grade[6]+"\tAverage="+grade[7]);
    }
}
