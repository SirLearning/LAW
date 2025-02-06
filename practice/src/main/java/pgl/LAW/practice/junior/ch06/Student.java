package pgl.LAW.practice.junior.ch06;

public class Student {
    private int SID;
    private String name;

    public Student(int m1, String m2, String m3, int m4) {
        SID = m1;
        name = m2;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
