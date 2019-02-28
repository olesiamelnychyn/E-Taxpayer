package com.company.Taxes.TaxesJuridicalPerson;

import com.company.Taxes.Tax;

public class ExciseTax implements Tax {
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
