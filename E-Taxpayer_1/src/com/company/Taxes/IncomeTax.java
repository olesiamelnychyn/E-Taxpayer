package com.company.Taxes;

import javax.swing.*;

public class IncomeTax implements Tax {
    double CleanIncome;
    double Persentage = 18;

    public double calculateTax() {
        double tax = CleanIncome * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }


    public IncomeTax(double Income, ValueAddedTax vat, ExciseTax et, double PrimeCost) {
        CleanIncome = Income - vat.calculateTax() - et.calculateTax() - PrimeCost;
    }
}
