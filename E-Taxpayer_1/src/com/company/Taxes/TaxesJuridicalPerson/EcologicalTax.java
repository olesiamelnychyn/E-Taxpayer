package com.company.Taxes.TaxesJuridicalPerson;

import com.company.Taxes.Tax;

public class EcologicalTax extends Tax {
    double Emissions;
    double CostperTon;

    public double calculateTax() {
        double tax = Emissions * CostperTon;
        return Math.round(tax * 100.0) / 100.0;
    }

    public EcologicalTax(double emissions, double costperTon) {
        Emissions = emissions;
        CostperTon = costperTon;
    }
}
