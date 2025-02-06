package pgl.LAW.practice.junior.ch06_4;

public class PayrollSystemTest {
    public static void main(String args[]){
        SalariedEmployee salariedEmployee = new SalariedEmployee("Junxia", "010010", 1000.00);
        HourlyEmployee hourlyEmployee = new HourlyEmployee("Yuxi", "010011", 99, 40);
        CommissionEmployee commissionEmployee = new CommissionEmployee("Wenbing", "010012", 30000, .06);
        BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Jiangwei", "010013", 1000, .04, 300);
        System.out.println("Employee salaries are as follows: \n");
        System.out.printf("%s\n%s: %,.2f\n\n", salariedEmployee, "Salary", salariedEmployee.earnings());
        System.out.printf("%s\n%s: %,.2f\n\n", hourlyEmployee, "Salary", hourlyEmployee.earnings());
        System.out.printf("%s\n%s: %,.2f\n\n", commissionEmployee, "Salary", commissionEmployee.earnings());
        System.out.printf("%s\n%s: %,.2f\n\n", basePlusCommissionEmployee, "Salary", basePlusCommissionEmployee.earnings());
    }
}
