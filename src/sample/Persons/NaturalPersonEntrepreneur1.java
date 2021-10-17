package sample.Persons;

import sample.Taxes.*;
import sample.Taxes.TaxesNaturalPersonEntrepreneur.SingleTax;
import sample.Taxes.Transaction;

import java.io.Serializable;
import java.util.ArrayList;

/** It is a class which represents a user - natural person-entrepreneur of type 1.<br>
 * It contains some common information like {@link NaturalPersonEntrepreneur1#Name}, {@link NaturalPersonEntrepreneur1#Surname}, {@link NaturalPersonEntrepreneur1#Years},
 * {@link NaturalPersonEntrepreneur1#BirthYear}, {@link NaturalPersonEntrepreneur1#Gender}.<br>
 * It also has all essential information about the user, on which all the taxes are based, and its taxes.<br>
 * And as it's an application, the class contains also user's {@link NaturalPersonEntrepreneur1#login} and {@link NaturalPersonEntrepreneur1#password} for access control.
 *
 * @see Tax
 */
public class NaturalPersonEntrepreneur1 implements NaturalPerson, Serializable {

    final private double MinSalary = 4173.0;
    //Common Information
    public String login;
    public String password;
    public String Name;
    public String Surname;
    public int Years;
    public int BirthYear;
    public String Gender;
    //Transactions
    /**
     * This is an ArrayList of all transactions.
     */
    public ArrayList<Transaction> transactions = new ArrayList<>();

    private String PassNumber;
    private String Location;
    //Taxes
    public Tax singleTax;
    public Tax singleSocialContribution;
    //For Taxes
    public double CirculationOfMoney;
    private int IdentificationCode;

    //Constructor

    /**
     * This is a constructor, which requires a common information about the user.
     *
     * @param login              is a one of two fields which are used while authorisation in the application ({@link NaturalPersonEntrepreneur1#login}).
     * @param password           is a one of two fields which are used while authorisation in the application ({@link NaturalPersonEntrepreneur1#password}).
     * @param name               is a field which keeps person's name ({@link NaturalPersonEntrepreneur1#Name}).
     * @param surname            is a field which keeps person's sername ({@link NaturalPersonEntrepreneur1#Surname}).
     * @param years              is a field which keeps person's age ({@link NaturalPersonEntrepreneur1#Years}).
     * @param birthYear          is a field which keeps person's year of birth ({@link NaturalPersonEntrepreneur1#BirthYear}).
     * @param passNumber         is a field which keeps person's passport number ({@link NaturalPersonEntrepreneur1#PassNumber}).
     * @param gender             is a field which keeps person's gender ({@link NaturalPersonEntrepreneur1#Gender}).
     * @param location           is a field which keeps country where person lives ({@link NaturalPersonEntrepreneur1#Location}).
     * @param identificationCode is a field which keeps person's number in the state register of taxpayers ({@link NaturalPersonEntrepreneur1#IdentificationCode}).
     * @see Tax
     * @see NaturalPersonEntrepreneur1
     */
    public NaturalPersonEntrepreneur1(String login, String password, String name, String surname, int years, int birthYear, String passNumber,
                                      String gender, String location, int identificationCode) {
        this.login = login;
        this.password = password;
        Name = name;
        Surname = surname;
        Years = years;
        BirthYear = birthYear;
        PassNumber = passNumber;
        Gender = gender;
        Location = location;
        IdentificationCode = identificationCode;
    }

    //Methods

    /**
     * This method is made to create taxes of the user based on some additional information like:
     * {@link NaturalPersonEntrepreneur1#CirculationOfMoney} and {@link NaturalPersonEntrepreneur1#MinSalary}.
     *
     * @see NaturalPersonEntrepreneur1
     */
    public void CreateTaxes() {
        singleTax = new SingleTax(CirculationOfMoney);
        singleSocialContribution = new SingleSocialContribution(MinSalary);
    }

    public String GetPassNumber() {
        return PassNumber;
    }

    public String GetLocation() {
        return Location;
    }

    public int GetIdentificationCode() {
        return IdentificationCode;
    }

    /**
     * This method is made to set an additional information, on which almost all the taxes ({@link NaturalPersonEntrepreneur1#singleTax} and {@link NaturalPersonEntrepreneur1#singleSocialContribution}) are based on.
     *
     * @param circulationOfMoney is a field which represents the amount of money which the user's business earns in a month.
     * @see Tax
     * @see NaturalPersonEntrepreneur1
     */
    public void SetAdditionalInf(double circulationOfMoney) {
        CirculationOfMoney = circulationOfMoney;
        CreateTaxes();
    }
}

