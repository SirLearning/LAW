package pgl.LAW.practice.junior.ch06_3;

public class CommissionEmployee {
    private String name;    // name of employee
    private String ID;  // ID of employee
    private double grossSales;  // content of sale
    private double commissionRate;  // rate of getting more money
    public CommissionEmployee(String name, String ID, double sales, double rate) {
        setName(name);
        setID(ID);
        setGrossSales(sales);
        setCommissionRate(rate);
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
    public double earnings(){
        return grossSales*commissionRate;
    }
    public String toString(){
        return String.format("%s: %s\n%s: %s\n%s: %.2f\n%s: %.2f",
                "Employee name", name,
                "Employee ID", ID,
                "Gross sales", grossSales,
                "Commission rate", commissionRate);
    }
}
