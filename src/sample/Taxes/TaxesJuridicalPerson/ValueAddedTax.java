package sample.Taxes.TaxesJuridicalPerson;

import sample.Taxes.Tax;

import java.text.DecimalFormat;

public class ValueAddedTax extends Tax {
    double Income;
    double Persentage = 20;

    public double calculateTax() {
        double tax = Income / (1.2) * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public ValueAddedTax(double Income) {
        this.Income = Income;
    }
}
