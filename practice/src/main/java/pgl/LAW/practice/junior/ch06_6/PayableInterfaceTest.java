package pgl.LAW.practice.junior.ch06_6;

public class PayableInterfaceTest {
    public static void main(String args[]){
        Payable payableObjects[] = new Payable[4];
        payableObjects[0] = new Invoice("01234", "Hardware", 2, 375.00);
        payableObjects[1] = new Invoice("56789", "USB", 4, 79.00);
        payableObjects[2] = new SalariedEmployee("Xiaoli", "010010", 2000.00);
        payableObjects[3] = new SalariedEmployee("Xiaojia", "010011", 1200.00);
        System.out.println("Invoice and employee salary: \n");
        for (Payable currentPayable:payableObjects){
            System.out.printf("%s \n%s: $ %,.2f\n\n", currentPayable.toString(),
                    "Should pay", currentPayable.getPaymentAmount());
        }
    }
}
