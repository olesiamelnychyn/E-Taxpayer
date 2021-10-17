package sample.Persons;

import sample.Taxes.*;
import sample.Taxes.TaxesJuridicalPerson.*;
import sample.Taxes.Transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * It is a class which represents a user - juridical person.<br>
 * It contains some basic information like {@link JuridicalPerson#Title}, {@link JuridicalPerson#BirthYear}, {@link JuridicalPerson#Location}, {@link JuridicalPerson#OSREOUCode}.<br>
 * It also has all essential information about the user, on which all the taxes are based, and its {@link JuridicalPerson#Taxes}.<br>
 * And as it's an application, the class contains also user's {@link JuridicalPerson#login} and {@link JuridicalPerson#password} for access control.
 *
 */
public class JuridicalPerson implements Serializable {
    public String login;
    public String password;
    public String Title;
    public int BirthYear;
    private String Location;
    /**
     * This is an ArrayList of all transactions.
     */
    public ArrayList<Transaction> transactions = new ArrayList<>();


    //Taxes:
    public ValueAddedTax VAT;
    public ExciseTax ExciseTax;
    public Tax IncomeTax;
    public Tax MineralResourcesTax;
    public Tax StateDuty;
    public Tax EcologicalTax;
    public Tax SingleSocialContribution;
    public Tax IncomeNatPerTax;
    public Tax MilitaryTax;
    public Tax RealtyTax;
    public Tax LandTax;
    public Tax TouristTax;
    public List<Tax> Taxes = new ArrayList<>();

    //For Taxes
    public double Income;
    public double PrimeCost;
    public boolean excise = false;
    public double PercentageofExcise;
    public boolean mineralUse = false;
    public double PercentageForMinRes = 10;
    public double Emissions;
    public double CostperTon = 10;
    public double Salaries;
    public double Square;
    public boolean touristic = false;
    public int NumberOfTourists;

    //Transactions
    /**
     * The only state register of enterprises and organizations of Ukraine.
     */
    private int OSREOUCode;

    /**
     * Constructor which determines a common information about the user of juridical type.
     *
     * @param login      is a one of two fields which are used while authorisation in the application ({@link JuridicalPerson#login}).
     * @param password   is a one of two fields which are used while authorisation in the application ({@link JuridicalPerson#password}).
     * @param title      is a field which keeps the title ({@link JuridicalPerson#Title}).
     * @param birthYear  is a field which keeps a year of initiation ({@link JuridicalPerson#BirthYear}).
     * @param location   is a field which keeps a contry where it is placed ({@link JuridicalPerson#Location}).
     * @param osreouCode is a field which keeps a number of the user in state register ({@link JuridicalPerson#OSREOUCode}).
     * @see JuridicalPerson
     */
    public JuridicalPerson(String login, String password, String title, int birthYear, String location, int osreouCode) {
        this.login = login;
        this.password = password;
        Title = title;
        BirthYear = birthYear;
        Location = location;
        OSREOUCode = osreouCode;
    }

    /**
     * This method is made to create taxes at the moment the user provide an additional information about its itself.
     * Not all taxes should be created, it depends on the type of user's activity.
     *
     * @see JuridicalPerson
     * @see Tax
     */
    public void CreateTaxes() {
        VAT = new ValueAddedTax(Income);

        if (excise) {
            ExciseTax = new ExciseTax(Income * PercentageofExcise);
            IncomeTax = new IncomeTaxofJuridicalPerson(Income, VAT, ExciseTax, PrimeCost);
        } else {
            IncomeTax = new IncomeTaxofJuridicalPerson(Income, VAT, PrimeCost);
        }
        if (mineralUse) {
            MineralResourcesTax = new RentForUseOfMineralResources(Income, PercentageForMinRes);
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
        Taxes.clear();
        Taxes.add(VAT);
        Taxes.add(ExciseTax);
        Taxes.add(IncomeTax);
        Taxes.add(MineralResourcesTax);
        Taxes.add(EcologicalTax);
        Taxes.add(SingleSocialContribution);
        Taxes.add(IncomeNatPerTax);
        Taxes.add(MilitaryTax);
        Taxes.add(RealtyTax);
        Taxes.add(LandTax);
        Taxes.add(TouristTax);
    }

    public String GetLocation() {
        return Location;
    }

    public int GetOSREOUCode() {
        return OSREOUCode;
    }

    /**
     * This method is made to create a special type of taxes - State Duty, which is not obligatory and must be paid only once, when it is caused.
     *
     * @param SellPrice is a field on which the tax is based on.
     * @see JuridicalPerson
     * @see Tax
     */
    public void CreateStateDuty(double SellPrice) {
        StateDuty = new StateDuty(SellPrice);
    }

    /**
     * This method is made to fill the addition information, which is provided while running the application in userpage for calculating taxes.
     * After saving all the data it also calls method {@link JuridicalPerson#CreateTaxes()}.
     *
     * @param income           is a field with essential information for such Taxes as: {@link JuridicalPerson#VAT}, {@link JuridicalPerson#ExciseTax}, {@link JuridicalPerson#IncomeTax},
     *                         {@link JuridicalPerson#MineralResourcesTax}, {@link JuridicalPerson#IncomeNatPerTax}.
     * @param primeCost        is a field with essential information for such Taxes as: {@link JuridicalPerson#IncomeTax}.
     * @param excise           is a field with essential information for such Taxes as: {@link JuridicalPerson#ExciseTax}.
     * @param mineralUse       is a field with essential information for such Taxes as: {@link JuridicalPerson#MineralResourcesTax}.
     * @param emissions        is a field with essential information for such Taxes as: {@link JuridicalPerson#EcologicalTax}.
     * @param salaries         is a field with essential information for such Taxes as: {@link JuridicalPerson#SingleSocialContribution}, {@link JuridicalPerson#MilitaryTax}.
     * @param square           is a field with essential information for such Taxes as: {@link JuridicalPerson#RealtyTax}, {@link JuridicalPerson#LandTax}.
     * @param touristic        is a field with essential information for such Taxes as: {@link JuridicalPerson#TouristTax}.
     * @param numberOfTourists is a field with essential information for such Taxes as: {@link JuridicalPerson#TouristTax}.
     * @param Percentageexcise is a field with essential information for such Taxes as: {@link JuridicalPerson#ExciseTax}.
     * @see JuridicalPerson
     * @see Tax
     */
    public void SetAdditionalInf(double income, double primeCost, boolean excise, boolean mineralUse,
                                 double emissions, double salaries, double square, boolean touristic,
                                 int numberOfTourists, double Percentageexcise) {
        Income = income;
        PrimeCost = primeCost;
        this.excise = excise;
        this.mineralUse = mineralUse;
        Emissions = emissions;
        Salaries = salaries;
        Square = square;
        this.touristic = touristic;
        NumberOfTourists = numberOfTourists;
        PercentageofExcise = Percentageexcise / 100;
        CreateTaxes();
    }
}
