package pgl.LAW.practice.junior.ch06_6;

public class SalariedEmployee extends Employee{
    private double weeklySalary;
    public SalariedEmployee(String name, String ID, double salary){
        super(name, ID);
        setWeeklySalary(salary);
    }
    public void setWeeklySalary(double salary){
        weeklySalary = salary;
    }
    public double getWeeklySalary(){
        return weeklySalary;
    }
    public double getPaymentAmount(){
        return getWeeklySalary();
    }
    public String toString(){
        return String.format("Weekly salaried employ: \n%s%s: $ %,.2f", super.toString(), "Salary", getWeeklySalary());
    }
}
