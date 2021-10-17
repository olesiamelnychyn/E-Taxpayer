package sample.Taxes.TaxesJuridicalPerson;

import sample.Taxes.Tax;

public class ExciseTax extends Tax {
    double Income;
    double Persentage=40;

    public double calculateTax() {
        double tax = Income * (Persentage/100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public ExciseTax(double Income) {
        this.Income = Income;
    }
}
