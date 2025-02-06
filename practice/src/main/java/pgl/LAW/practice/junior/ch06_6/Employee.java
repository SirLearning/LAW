package pgl.LAW.practice.junior.ch06_6;

public abstract class Employee implements Payable{
    private String name;
    private String ID;
    public Employee(String name, String ID){
        this.name = name;
        this.ID = ID;
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
    public String toString(){
        return String.format("%s: %s\n%s: %s\n", "Employee name", name, "Employee ID", ID);
    }
}
