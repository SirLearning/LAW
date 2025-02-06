package pgl.LAW.practice.junior.ch04;

public class StudentPoll {
    public static void main(String args[]) {
        int responses[] = {1,0,2};
        int frequency[] = new int[3];
        for (int i=0; i<responses.length; i++)
            ++frequency[responses[i]];
        System.out.printf("%s%10s\n", "score", "people number");
        for (int j=1; j<frequency.length; j++)
            System.out.printf("%6d%10d\n", j, frequency[j]);

        int a[] = new int[10];
        change(a);

        int c[][] = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.print(c[1][2]);
    }

    public static void change(int[] a) {
        int b[];
        b = a;
        b[1] = 12;
        System.out.println(a[1]);
    }
}
