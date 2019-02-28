package com.company.Taxes.TaxesJuridicalPerson;

import com.company.Taxes.Tax;

public class IncomeTaxofJuridicalPerson implements Tax {
    double CleanIncome;
    double Persentage = 18;

    public double calculateTax() {
        double tax = CleanIncome * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }


    public IncomeTaxofJuridicalPerson(double Income, ValueAddedTax vat, ExciseTax et, double PrimeCost) {
        CleanIncome = Income - vat.calculateTax() - et.calculateTax() - PrimeCost;
    }
}
