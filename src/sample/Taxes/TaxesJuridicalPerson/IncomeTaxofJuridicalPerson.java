package sample.Taxes.TaxesJuridicalPerson;

import sample.Taxes.Tax;

public class IncomeTaxofJuridicalPerson extends Tax {
    double CleanIncome;
    double Persentage = 18;

    public double calculateTax() {
        double tax = CleanIncome * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }


    public IncomeTaxofJuridicalPerson(double Income, ValueAddedTax vat, ExciseTax et, double PrimeCost) {
        CleanIncome = Income - vat.calculateTax() - et.calculateTax() - PrimeCost;
    }
    public IncomeTaxofJuridicalPerson(double Income, ValueAddedTax vat, double PrimeCost) {
        CleanIncome = Income - vat.calculateTax() - PrimeCost;
    }
}
