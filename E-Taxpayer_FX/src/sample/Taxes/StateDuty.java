//this tax is paid by both Physical and Juridical person
package sample.Taxes;

public class StateDuty extends Tax {
    double Price;
    double Persentage = 1;

    public double calculateTax() {
        double tax = Price * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public StateDuty(double price) {
        Price = price;
    }
}
