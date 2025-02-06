package pgl.LAW.practice.junior.ch04;

public class DoWhileSum {
    public static void main(String args[]) {
        int i = 1;
        int n = 100;
        int sum = 0;
        do {
            sum += i;
            i++;
        } while(i<n);
        System.out.println("sum = "+sum);
        System.out.println("after loop, i = "+i);
    }
}
