package sample.Persons;
// it is not finished yet

import sample.Taxes.*;
import sample.Taxes.TaxesJuridicalPerson.*;
public class JuridicalPerson {
    public String login;
    public String password;
    String Title;
    int BirthYear;
    private String Location;
    private int OSREOUCode;  //the only state register of enterprises and organizations of Ukraine

    //Taxes:
    ValueAddedTax VAT;
    ExciseTax ExciseTax;
    Tax IncomeTax;
    Tax MineralResourcesTax;
    Tax StateDuty;
    Tax EcologicalTax;
    Tax SingleSocialContribution;
    Tax IncomeNatPerTax;
    Tax MilitaryTax;
    Tax RealtyTax;
    Tax LandTax;
    Tax TouristTax;

    //For Taxes
    double Income;
    double PrimeCost;
    boolean excise = false;
    boolean mineralUse = false;
    double PersentageForMinRes;
    double Emissions;
    double CostperTon;
    double Salaries;
    double Square;
    boolean touristic;
    int NumberOfTourists;


    //Methods
    public void CreateTaxes() {
        VAT = new ValueAddedTax(Income);
        if (excise) {
            ExciseTax = new ExciseTax(Income);
        }
        IncomeTax = new IncomeTaxofJuridicalPerson(Income, VAT, ExciseTax, PrimeCost);
        if (mineralUse) {
            MineralResourcesTax = new RentForUseOfMineralResources(Income, PersentageForMinRes);
        }

        EcologicalTax = new EcologicalTax(Emissions, CostperTon);
        SingleSocialContribution = new SingleSocialContribution(Salaries);
        IncomeNatPerTax = new IncomeTaxofNaturalPersons(Income);
        MilitaryTax = new MilitaryFee(Salaries);
        RealtyTax = new RealEstateTaxforJuridicalPerson(Square);
        LandTax = new LandTaxforJuridicalPerson(Square);
        if (touristic) {
            TouristTax = new TouristTax(NumberOfTourists);
        }

    }

    public void CreateStateDuty(double SellPrice) {
        StateDuty = new StateDuty(SellPrice);
    }

    public String GetLocation() {
        return Location;
    }

    public int GetOSREOUCode() {
        return OSREOUCode;
    }

    public void SetAdditionalInf(double income, double primeCost, boolean excise, boolean mineralUse, double persentageForMinRes,
                                 double emissions, double costperTon, double salaries, double square, boolean touristic,
                                 int numberOfTourists) {
        Income = income;
        PrimeCost = primeCost;
        this.excise = excise;
        this.mineralUse = mineralUse;
        PersentageForMinRes = persentageForMinRes;
        Emissions = emissions;
        CostperTon = costperTon;
        Salaries = salaries;
        Square = square;
        this.touristic = touristic;
        NumberOfTourists = numberOfTourists;
        CreateTaxes();
    }

    public JuridicalPerson(String title, int birthYear, String location, int osreouCode) {
        Title = title;
        BirthYear = birthYear;
        Location = location;
        OSREOUCode = osreouCode;
    }
}
