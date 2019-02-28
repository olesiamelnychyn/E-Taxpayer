package com.company.Taxes;

public class RentForUseOfMineralResources implements Tax {
    double Income;
    double Persentage=0; //at the beginning persentage is 0, because it varies due to the type of mineral resourse

    public double calculateTax() {
        double tax = Income*(Persentage/100);
        return tax;
    }

    public RentForUseOfMineralResources(double income, double persentage){
        Income=income;
        Persentage=persentage;
    }
}
