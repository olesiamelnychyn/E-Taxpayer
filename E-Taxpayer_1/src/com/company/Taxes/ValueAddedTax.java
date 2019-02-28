package com.company.Taxes;

import java.text.DecimalFormat;

public class ValueAddedTax implements Tax {
    double Income;
    double Persentage;

    public double calculateTax() {
        double tax = Income / (1.2) * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public void setTaxPersentage() {
        Persentage = 20;
    }

    public ValueAddedTax(double Income) {
        this.Income = Income;
    }
}
