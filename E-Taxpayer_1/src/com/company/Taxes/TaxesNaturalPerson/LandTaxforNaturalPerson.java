package com.company.Taxes.TaxesNaturalPerson;

import com.company.Taxes.Tax;

public class LandTaxforNaturalPerson extends Tax {
    double Square;
    double MinSalary = 4173;
    double Persentage = 0.1;

    public double calculateTax() {
        double tax = (Square/100) * MinSalary * (Persentage/100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public LandTaxforNaturalPerson(double square){
        Square = square;
    }
}
