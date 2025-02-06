package pgl.LAW.practice.junior.ch06_3;

public class CommissionEmployeeTest {
    public static void main(String args[]){
        CommissionEmployee employee = new CommissionEmployee("Zhang Zhifeng", "000001", 1000000, .06);
        System.out.println("Basic information for employee is: \n");
        System.out.print(employee.toString());
        employee.setGrossSales(500000);
        employee.setCommissionRate(.1);
        System.out.printf("\n%s:\n\n%s\n", "updated information", employee);
    }
}
