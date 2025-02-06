package pgl.LAW.practice.junior.ch04;

public class GradeStat {
    private String courseName;
    private int grades[];

    // construct GradeStat class, creating an instance of GradeStat class and initiating the courseName and grades attributes
    public GradeStat(String name, int gradesArray[]) {
        courseName = name;
        grades = gradesArray;
    }

    public void setCourseName(String name) {
        courseName = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void displayMessage() {
        System.out.printf("welcome to check the grade of: \n%s!\n\n", getCourseName());
    }

    public void processGrades () {
        outputGrades();
        System.out.printf("\naverage grade is: ",getAverage());
        System.out.printf("the lowest grade is: %d\nthe highest grade is: %d\n\n", getMinimum(), getMaximum());
        outputBarChart();
    }

    public int getMinimum () {
        int lowGrade = grades[0];
        for (int grade : grades) {
            if (grade < lowGrade)
                lowGrade = grade;
        }
        return lowGrade;
    }

    public int getMaximum () {
        int highGrade = grades[0];
        for (int grade:grades) {
            if (grade>highGrade)
                highGrade = grade;
        }
        return highGrade;
    }

    public double getAverage () {
        int total = 0;
        for (int grade: grades)
            total += grade;
        return (double) total/grades.length;
    }

    public void outputBarChart () {
        System.out.println("grade distribution:");
        int frequency[] = new int[11];
        for (int grade:grades)
            ++frequency[grade/10];
        for (int count=0; count<frequency.length; count++) {
            if (count==10)
                System.out.printf("%5d: ", 100);
            else
                System.out.printf("%02d-%02d: ", count*10, count*10+9);
            for (int stars=0; stars<frequency[count]; stars++)
                System.out.print("*");
            System.out.println();
        }
    }

    public void outputGrades() {
        System.out.println("Grades are as following:\n");
        for (int student=0; student<grades.length; student++)
            System.out.printf("Student %2d: %3d\n", student+1, grades[student]);
    }

    public static void main(String args[]) {
        int gradeArray[] = {90, 89, 36, 89, 87, 76, 89, 92, 12, 90};
        GradeStat myGrade = new GradeStat("Java program design grade", gradeArray);
        myGrade.displayMessage();
        myGrade.processGrades();
    }
}
