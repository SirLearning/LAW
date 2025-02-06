package pgl.LAW.practice.junior.ch04;

public class MaxMin {
    public static void main(String args[]) {
        int max,min;
        int x=16,y=19,z=6;
        if (x>y)
            max = x;
        else
            max = y;
        if (z>max)
            max = z;
        System.out.println("the maximum number is: "+max);
        min = x<y?x:y;
        min = z<min?z:min;
        System.out.println("the minimum number is: "+min);
    }
}
