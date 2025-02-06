package pgl.LAW.practice.junior.ch06_4;

public abstract class Employee {
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
        return String.format("%s:%s\n%s:%s", "Employee name", getName(), "Employee ID", getID());
    }
    public abstract double earnings();
}
