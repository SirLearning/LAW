package pgl.LAW.practice.junior.ch04;

public class GradeBookTest {
    public static void main(String args[]) {
        int gradeArray[][] = {{87, 96, 70}, {68, 87, 90}, {94, 100, 90}};
        GradeBook myGradeBook = new GradeBook("Java program design", gradeArray);
        myGradeBook.displayMessage();
        myGradeBook.processGrades();
    }
}
