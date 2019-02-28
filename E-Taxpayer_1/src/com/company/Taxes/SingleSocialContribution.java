//this tax is paid by both Jurical anb Natural Entreprenuer person
package com.company.Taxes;

public class SingleSocialContribution extends Tax {
    double SumofSalaries;
    double Persentage = 22;

    public double calculateTax() {
        double tax = SumofSalaries * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public SingleSocialContribution(double sumofSalaries) {
        SumofSalaries = sumofSalaries;
    }
}
