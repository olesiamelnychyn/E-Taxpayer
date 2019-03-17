package sample.Taxes.TaxesJuridicalPerson;

import sample.Taxes.Tax;

public class RentForUseOfMineralResources extends Tax {
    double Income;
    double Persentage = 0; //at the beginning persentage is 0, because it varies due to the type of mineral resourse

    public double calculateTax() {
        double tax = Income * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public RentForUseOfMineralResources(double income, double persentage) {
        Income = income;
        Persentage = persentage;
    }
}
