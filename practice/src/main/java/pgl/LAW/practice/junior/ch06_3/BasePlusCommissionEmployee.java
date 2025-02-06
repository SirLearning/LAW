package pgl.LAW.practice.junior.ch06_3;

public class BasePlusCommissionEmployee {
    private String name;
    private String ID;
    private double grossSales;
    private double commissionRate;
    private double baseSalary;
    public BasePlusCommissionEmployee(String name, String ID, double sales, double rate, double salary){
        setName(name);
        setID(ID);
        setGrossSales(sales);
        setCommissionRate(rate);
        setBaseSalary(salary);
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setID(String ID){
        this.ID = ID;
    }
    public String getID(){
        return ID;
    }
    public void setGrossSales(double sales){
        grossSales = (sales<0.0)?0.0:sales;
    }
    public double getGrossSales(){
        return grossSales;
    }
    public void setCommissionRate(double rate){
        commissionRate = (rate>0.0&&rate<=1.0)?rate:0.0;
    }
    public double getCommissionRate(){
        return commissionRate;
    }
    public void setBaseSalary(double salary){
        baseSalary = (salary<0.0)?0.0:salary;
    }
    public double getBaseSalary(){
        return baseSalary;
    }
    public double earnings(){
        return baseSalary + (grossSales*commissionRate);
    }
    public String toString(){
        return String.format("%s: %s\n%s: %s\n%s: %.2f\n%s: %.2f\n%s: %.2f",
                "Employee name", name,
                "Employee ID", ID,
                "Gross sales", grossSales,
                "Commission rate", commissionRate,
                "Basic salary", baseSalary);
    }
}
