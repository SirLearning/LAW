package pgl.LAW.practice.junior.ch06_4;

public class SalariedEmployee extends Employee{
    private double weeklySalary;
    public SalariedEmployee(String name, String ID, double salary){
        super(name, ID);
        setWeeklySalary(salary);
    }
    public void setWeeklySalary(double salary){
        weeklySalary = salary<0.0?0.0:salary;
    }
    public double getWeeklySalary(){
        return weeklySalary;
    }
    public double earnings(){
        return getWeeklySalary();
    }
    public String toString(){
        return String.format("Salary for w1eekly employee if: \n%s %s: %,.2f", super.toString(), "salary", getWeeklySalary());
    }
}
