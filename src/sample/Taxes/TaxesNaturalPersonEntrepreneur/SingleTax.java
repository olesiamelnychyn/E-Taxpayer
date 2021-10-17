package sample.Taxes.TaxesNaturalPersonEntrepreneur;

import sample.Taxes.Tax;

public class SingleTax extends Tax {
    double CirculationOfMoney;
    double MinSalary = 4173;
    double Persentage1 = 10;
    double Persentage2 = 20;
    double Persentage3 = 50;

    public double calculateTax() {
        double tax = 0;
        if (CirculationOfMoney < 200000) {
            tax = MinSalary * (Persentage1 / 100);
        } else if (CirculationOfMoney < 1500000) {
            tax = MinSalary * (Persentage2 / 100);
        } else if (CirculationOfMoney < 5000000) {
            tax = MinSalary * (Persentage3 / 100);
        }
        return Math.round(tax * 100.0) / 100.0;
    }

    public SingleTax(double circulationOfMoney) {
        CirculationOfMoney = circulationOfMoney;
    }
}
