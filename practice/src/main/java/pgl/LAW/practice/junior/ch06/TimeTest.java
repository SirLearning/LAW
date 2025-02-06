package pgl.LAW.practice.junior.ch06;

public class TimeTest {
    public static void main(String args[]) {
        Time t1 = new Time(8, 16, 36);
        Time t2 = new Time(2, 66, 66);
        Time t3 = new Time(t1);
        Time t4 = t1;
        System.out.printf("%s\n", t1.toUniversalString());
        System.out.printf("%s\n", t1.toString());
        System.out.printf("%s\n", t2.toUniversalString());
        System.out.printf("%s\n", t2.toString());
        System.out.printf("%s\n", t3.toUniversalString());
        System.out.printf("%s\n", t3.toString());
        System.out.println(t3==t1?"=":"!=");
        System.out.println(t4==t1?"=":"!=");
    }
}
