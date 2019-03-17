package sample.Taxes.TaxesJuridicalPerson;
import sample.Taxes.Tax;

public class RealEstateTaxforJuridicalPerson extends Tax {
    double Square;
    double MinSalary = 4173;
    double Persentage =1;

    public double calculateTax() {
        double tax = MinSalary*(Persentage/100)*Square/12;
        return Math.round(tax * 100.0) / 100.0;
    }

    public RealEstateTaxforJuridicalPerson(double square){
        Square=square;
    }
}
