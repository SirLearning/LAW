package pgl.LAW.practice.junior.ch06_4;

public class HourlyEmployee extends Employee{
    private double wage;
    private double hours;
    public HourlyEmployee(String name, String ID, double hourlyWage, double hoursWorked){
        super(name, ID);
        setWage(hourlyWage);
        setHours(hoursWorked);
    }
    public void setWage(double hourlyWage){
        wage = hourlyWage<0.0?0.0:hourlyWage;
    }
    public double getWage(){
        return wage;
    }
    public void setHours(double hoursWorked){
        hours = (hoursWorked>0.0 && hoursWorked<=168.0)?hoursWorked:0.0;
    }
    public double getHours(){
        return hours;
    }
    public double earnings(){
        if (getHours() <= 40)
            return getWage()*getHours();
        else
            return 40*getWage()+(getHours()-40)*getWage()*1.5;
    }
    public String toString(){
        return String.format("Salary for hourly employee is: \n%s %s: $ %,.2f; %s: %,.2f", super.toString(),
                "Salary", getWage(),
                "how many hours", getHours());
    }
}
