package sample.Taxes.TaxesJuridicalPerson;

import sample.Taxes.Tax;

public class MilitaryFee extends Tax {
    double SumofSalaries;
    double Persentage = 1.5;

    public double calculateTax() {
        double tax = SumofSalaries * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public MilitaryFee(double sumofSalaries) {
        SumofSalaries = sumofSalaries;
    }
}
