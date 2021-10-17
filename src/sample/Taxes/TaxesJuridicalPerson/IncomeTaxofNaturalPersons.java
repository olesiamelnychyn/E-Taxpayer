package sample.Taxes.TaxesJuridicalPerson;

import sample.Taxes.Tax;

public class IncomeTaxofNaturalPersons extends Tax {
    double Income;
    double Persentage = 18;

    public double calculateTax() {
        double tax = Income * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public IncomeTaxofNaturalPersons(double income) {
        Income = income;
    }
}
