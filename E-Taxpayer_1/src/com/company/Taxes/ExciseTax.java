package com.company.Taxes;

public class ExciseTax implements Tax {
    double Income;
    double Persentage;

    public double calculateTax() {
        double tax = Income * (Persentage/100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public void setTaxPersentage() {
        Persentage = 40;
    }

    public ExciseTax(double Income) {
        this.Income = Income;
    }
}
