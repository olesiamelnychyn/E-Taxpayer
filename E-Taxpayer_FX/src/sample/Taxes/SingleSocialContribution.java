//this tax is paid by both Jurical anb Natural Entreprenuer person
package sample.Taxes;

public class SingleSocialContribution extends Tax {
    double Salaries; //Salaries has different meaning for Juridical and Natural person, but still the fotmula is the same:
    // in case it is Juridical one Salaries means sum of salaries of the workers,
    // in case it is Natural person it means minimal salary
    double Persentage = 22;

    public double calculateTax() {
        double tax = Salaries * (Persentage / 100);
        return Math.round(tax * 100.0) / 100.0;
    }

    public SingleSocialContribution(double salaries) {
        Salaries = salaries;
    }

}
