//this tax is paid once a year
package sample.Taxes.TaxesPhysicalPerson;

import sample.Taxes.Tax;

public class LuxuryTax extends Tax {
    double Price;
    int Years;
    double MinSalary = 4173;

    public double calculateTax() {
        if (Years < 5 && Price > 320 * MinSalary)
           return 25000;
        else
           return 0;
    }

    public LuxuryTax(double price, int years) {
        Price = price;
        Years = years;
    }

}
