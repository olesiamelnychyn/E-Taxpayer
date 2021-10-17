//this tax is paid once a year
package sample.Taxes.TaxesPhysicalPerson;

import sample.Taxes.Tax;

public class RealEstateTaxforPhysicalPerson extends Tax {
    double Square;
    String TypeofRealty;
    double MinSalary = 4173;
    double Persentage = 1;
    double MinforFlat = 120;
    double MinforHouse = 240;

    public double calculateTax() {
        double tax = 0;
        if(TypeofRealty.equals("-")){
            tax=0;
        } else if (TypeofRealty.equals("flat") && Square - MinforFlat > 0) {
            tax = (Square - MinforFlat) * MinSalary * (Persentage / 100);
        } else if (TypeofRealty.equals("house") && Square - MinforHouse > 0) {
            tax = (Square - MinforHouse) * MinSalary * (Persentage / 100);
        }
        return Math.round(tax * 100.0) / 100.0;
    }

    public RealEstateTaxforPhysicalPerson( String typeofRealty, double square) {
        Square = square;
        TypeofRealty = typeofRealty;
    }
}
