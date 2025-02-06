package pgl.LAW.practice.junior.ch06_3;

public class BasePlusCommissionEmployee2 extends CommissionEmployee{
    private double baseSalary;
    public BasePlusCommissionEmployee2(String name, String ID, double sales, double rate, double salary) {
        super(name, ID, sales, rate);
        setBaseSalary(salary);
    }
    public void setBaseSalary(double salary){
        baseSalary = (salary<0.0)?0.0:salary;
    }
    public double getBaseSalary(){
        return baseSalary;
    }
    public double earnings(){
        return baseSalary + (getGrossSales()*getCommissionRate());
    }
    public String toString(){
        return String.format("%s: %s\n%s: %s\n%s: %0.2f\n%s: %0.2f\n%s: %0.2f",
                "Employee name", getName(),
                "Employee ID", getID(),
                "Gross sales", getGrossSales(),
                "Commission rate", getCommissionRate(),
                "Basic salary", baseSalary);
    }
}
