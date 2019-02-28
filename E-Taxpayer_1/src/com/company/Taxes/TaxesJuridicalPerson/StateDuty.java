package com.company.Taxes.TaxesJuridicalPerson;

import com.company.Taxes.Tax;

public class StateDuty implements Tax {
    double Price;
    double Persentage = 1;

    public double calculateTax() {
        double tax = Price * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public StateDuty(double price) {
        Price = price;
    }
}
