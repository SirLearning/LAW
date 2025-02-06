package pgl.LAW.practice.junior.ch06_4;

public class BasePlusCommissionEmployee extends CommissionEmployee{
    private double baseSalary;
    public BasePlusCommissionEmployee(String name, String ID, double sales, double rate, double salary){
        super(name, ID, sales, rate);
        setBaseSalary(salary);
    }
    public void setBaseSalary(double salary){
        baseSalary = salary<0.0?0.0:salary;
    }
    public double getBaseSalary(){
        return baseSalary;
    }
    public double earnings(){
        return getBaseSalary() * 1.15 + super.earnings();
    }
    public String toString(){
        return String.format("%s %s; %s: $ %,.2f", "Base salary plus commission: ", super.toString(),
                "Base salary", getBaseSalary());
    }
}
