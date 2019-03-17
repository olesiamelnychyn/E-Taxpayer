package sample.Taxes.TaxesPhysicalPerson;

import sample.Taxes.Tax;

public class LandTaxforPhysicalPerson extends Tax {
    double Square;
    double MinSalary = 4173;
    double Persentage = 0.1;

    public double calculateTax() {
        double tax = (Square/100) * MinSalary * (Persentage/100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public LandTaxforPhysicalPerson(double square){
        Square = square;
    }
}
