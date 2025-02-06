package pgl.LAW.practice.junior.ch04;

public class RotorString {
    public static void main(String args[]) {
        String str = "rotor";
        if (args.length > 0)
            str = args[0];
        System.out.println("str="+str);
        // algorithm 1
        boolean yes = true;
        int n = str.length();
        int i = 0;
        char ch1, ch2;
        while (yes && (i<n/2)) {
            ch1 = str.charAt(i);
            ch2 = str.charAt(n-i-1);
            System.out.println("ch1="+ch1+" ch2="+ch2);
            if (ch1==ch2)
                i++;
            else
                yes = false;
        }
        System.out.println("Algorithm 1: "+yes);
        //algorithm 2
        String temp="", sub1="";
        for (i=0; i<n; i++) {
            sub1 = str.substring(i, i+1);
            temp = sub1 + temp;
        }
        System.out.println("temp="+temp);
        System.out.println("Algorithm 2: "+str.equals(temp));
    }
}
