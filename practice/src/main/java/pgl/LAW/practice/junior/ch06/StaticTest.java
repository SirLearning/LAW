package pgl.LAW.practice.junior.ch06;

public class StaticTest {
    public static final double PI = Math.PI;
    public static String toChar(double x) {
        return Double.toString(x);
    }
    public final static int TOW = 2;
    public static void main(String args[]) {
        // the static method round of Math class
        System.out.println(Math.round(6.5));
        String s = toChar(12.6);
        System.out.println("e="+s);
    }
}
