package sample.Persons;

import sample.Taxes.*;
import sample.Taxes.TaxesPhysicalPerson.*;
import sample.Taxes.Transaction;
import java.io.Serializable;
import java.util.ArrayList;

/** It is a class which represents a user - natural person.<br>
 * It contains some common information like {@link PhysicalPerson#Name},  {@link PhysicalPerson#Surname},
 * {@link PhysicalPerson#Age}, {@link PhysicalPerson#BirthYear}, {@link PhysicalPerson#PassNumber}, {@link PhysicalPerson#Gender},
 * {@link PhysicalPerson#Location} and {@link PhysicalPerson#IdentificationCode}.<br>
 * It also has all essential information about the user, on which all the taxes are based, and its taxes.<br>
 * And as it's an application, the class contains also user's {@link PhysicalPerson#login} and {@link PhysicalPerson#password} for access control.
 *
 */
public class PhysicalPerson implements NaturalPerson, Serializable {

    //Common information
    public String login;
    public String password;
    public String Name;
    public String Surname;
    public int Age;
    public int BirthYear;
    private String PassNumber;
    public String Gender;
    private String Location;
    private int IdentificationCode;

    //Luxury Tax
    public double CarPrice;
    public int CarAge;

    //Realty Tax
    public String TypeofRealty; // flat or house
    public double SquareofRealty;

    //Land Tax
    public double LandSquare;

    //Taxes
    public Tax landTax;
    public Tax realtyTax;
    public Tax luxuryTax;
    public Tax stateDuty;

    //Transactions
    /**
     * This is an ArrayList of all transactions.
     */
    public ArrayList<Transaction> transactions = new ArrayList<>();

    //Constructor
    /**
     * This is a constructor, which requires a common information about the user.
     *
     * @param login              is a one of two fields which are used while authorisation in the application ({@link PhysicalPerson#login}).
     * @param password           is a one of two fields which are used while authorisation in the application ({@link PhysicalPerson#password}).
     * @param name               is a field which keeps person's name ({@link PhysicalPerson#Name}).
     * @param surname            is a field which keeps person's sername ({@link PhysicalPerson#Surname}).
     * @param age                is a field which keeps person's age ({@link PhysicalPerson#Age}).
     * @param birthYear          is a field which keeps person's year of birth ({@link PhysicalPerson#BirthYear}).
     * @param passNumber         is a field which keeps person's passport number ({@link PhysicalPerson#PassNumber}).
     * @param gender             is a field which keeps person's gender ({@link PhysicalPerson#Gender}).
     * @param location           is a field which keeps country where person lives ({@link PhysicalPerson#Location}).
     * @param identificationCode is a field which keeps person's number in the state register of taxpayers ({@link PhysicalPerson#IdentificationCode}).
     * @see PhysicalPerson
     */
    public PhysicalPerson(String login, String password, String name, String surname, int age, int birthYear, String passNumber,
                          String gender, String location, int identificationCode) {
        this.login = login;
        this.password = password;
        Name = name;
        Surname = surname;
        Age = age;
        BirthYear = birthYear;
        PassNumber = passNumber;
        Gender = gender;
        Location = location;
        IdentificationCode = identificationCode;
    }

    //methods
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
     * This method is made to create taxes of the user based on some additional information like:
     * {@link PhysicalPerson#LandSquare} (for {@link PhysicalPerson#landTax}), {@link PhysicalPerson#TypeofRealty} (for {@link PhysicalPerson#realtyTax}),
     * {@link PhysicalPerson#SquareofRealty} (for {@link PhysicalPerson#realtyTax}), {@link PhysicalPerson#CarPrice} (for {@link PhysicalPerson#luxuryTax})
     * and {@link PhysicalPerson#CarAge} (for {@link PhysicalPerson#luxuryTax}).
     *
     * @see PhysicalPerson
     * @see Tax
     */
    public void CreateTaxes() {
        landTax = new LandTaxforPhysicalPerson(LandSquare);
        realtyTax = new RealEstateTaxforPhysicalPerson(TypeofRealty, SquareofRealty);
        luxuryTax = new LuxuryTax(CarPrice, CarAge);
    }

    /**
     * This method is made to create a special type of taxes - State Duty, which is not obligatory and must be paid only once, when it is caused.
     *
     * @param SellPrice is a field on which the tax is based on.
     * @see PhysicalPerson
     * @see Tax
     */
    public void CreateStateDuty(double SellPrice) {
        stateDuty = new StateDuty(SellPrice);
        stateDuty.calculateTax();
    }



    /**
     * This method is made to set an additional information, on which almost all the taxes of the user are based on.
     *
     * @param carPrice        is a field which keeps information about how much user's car cost (for {@link PhysicalPerson#luxuryTax}).
     * @param carAge          is a field which keeps information about how old user's car is (for {@link PhysicalPerson#luxuryTax}).
     * @param typeofRealty    is a field which keeps information about user's realty: it can be "flat" or "house" (for {@link PhysicalPerson#realtyTax}).
     * @param sdquareofRealty is a field which keeps information about square of user's realty (for {@link PhysicalPerson#realtyTax}).
     * @param landSquare      is a field which keeps information about additional land, if there is no, than landSquare=0 (for {@link PhysicalPerson#landTax}).
     * @see PhysicalPerson
     * @see Tax
     */
    public void SetAdditionalInf(double carPrice, int carAge, String typeofRealty,
                                 double sdquareofRealty, double landSquare) {
        CarPrice = carPrice;
        CarAge = carAge;
        TypeofRealty = typeofRealty;
        SquareofRealty = sdquareofRealty;
        LandSquare = landSquare;
        CreateTaxes();

    }
}
