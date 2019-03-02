//this tax is paid once a year
package com.company.Taxes.TaxesJuridicalPerson;

import com.company.Taxes.Tax;

public class LandTaxforJuridicalPerson extends Tax {
    double Square;
    double LandPricePerM = 100;
    double LandPrice = LandPricePerM * Square;
    double Persentage = 8;

    public double calculateTax() {
        double tax = LandPrice * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public LandTaxforJuridicalPerson(double square) {
        Square = square;
    }
}
