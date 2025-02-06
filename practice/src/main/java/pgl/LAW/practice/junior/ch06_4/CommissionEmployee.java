package pgl.LAW.practice.junior.ch06_4;

public class CommissionEmployee extends Employee{
    private double grossSales;
    private double commissionRate;
    public CommissionEmployee(String name, String ID, double sales, double rate){
        super(name, ID);
        setGrossSales(sales);
        setCommissionRate(rate);
    }
    public void setCommissionRate(double rate){
        commissionRate = (rate>0.0&&rate<1.0)?rate:0.0;
    }
    public double getCommissionRate(){
        return commissionRate;
    }
    public void setGrossSales(double sales){
        grossSales = sales<0.0?0.0:sales;
    }
    public double getGrossSales(){
        return grossSales;
    }
    public double earnings(){
        return getCommissionRate()*getGrossSales();
    }
    public String toString(){
        return String.format("%s: \n%s\n%s: %,.2f; %s: %.2f",
                "Salary for commission employee is: ", super.toString(),
                "Gross sales: ", getGrossSales(),
                "Commission rate: ", getCommissionRate());
    }
}
