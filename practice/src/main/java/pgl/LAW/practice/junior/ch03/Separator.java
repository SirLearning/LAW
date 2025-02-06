package pgl.LAW.practice.junior.ch03;

public class Separator {
    public static void main(String arg[]) {
        int i,sum = 0;
        int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (i=0;i<a.length;i++) {
            sum += a[i];
        }
        System.out.println(sum);
    }
}

