package pgl.LAW.practice.junior.ch04;

public class ForInterest {
    public static void main(String args[]) {
        double amount;
        double principal = 1000000.0;
        double rate = 0.0369;
        System.out.printf("%4s%20s\n", "year", "saving");
        for (int year=1; year<=10; year++) {
            amount = principal * Math.pow(1+rate, year);
            System.out.printf("%4d%,20.2f\n", year, amount);
        }
    }
}
