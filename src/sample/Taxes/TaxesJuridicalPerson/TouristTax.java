package sample.Taxes.TaxesJuridicalPerson;

import sample.Taxes.Tax;

public class TouristTax extends Tax {
    int NumberofTourists;
    double rate = 40;

    public double calculateTax() {
        double tax = NumberofTourists*rate;
        return Math.round(tax * 100.0) / 100.0;
    }

    public TouristTax(int numberofTourists){
        NumberofTourists=numberofTourists;
    }
}
