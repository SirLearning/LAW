package pgl.LAW.practice.junior.ch03;

public class TypeConversion {
    public static void main(String arg[]) {
        int x=100;
        long y=x;
        System.out.println("from int "+x+" to long "+y);
        double d=56789;
        int a=(int)d;
        System.out.println("from double "+d+" to int "+a);
    }
}
