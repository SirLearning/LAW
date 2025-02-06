package pgl.LAW.practice.junior.ch06_3;

public class BasePlusCommissionEmployeeTest {
    public static void main(String args[]) {
        BasePlusCommissionEmployee employee = new BasePlusCommissionEmployee("Zhang Zhifeng", "000002", 1000000, .06, 3600);
        System.out.println("Basic information for employee is: \n");
        System.out.print(employee.toString());
        System.out.printf("\n%s%.2f\n", "earning is: ", employee.earnings());
        employee.setGrossSales(2000000);
        System.out.printf("\n%s:\n\n%s\n", "updated information", employee);
        System.out.printf("%s%.2f\n", "earning is: ", employee.earnings());
    }
}
