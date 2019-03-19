package sample.Persons;

import sample.Taxes.*;
import sample.Taxes.TaxesPhysicalPerson.*;

public class PhysicalPerson implements NaturalPerson {

    //common information
    public String login;
    public String password;
    public String Name;
    public String Sername;
    public int Age;
    public int BirthYear;
    private String PassNumber;
    public String Gender;
    private String Location;
    private int IdentificationCode;

    //Luxury
    public double CarPrice;
    public int CarAge;

    //Realty
    public String TypeofRealty = null; // flat or house
    public double SquareofRealty;

    //Land
    public double LandSquare;

    //Taxes (all taxes will be in a Collection soon)
    public Tax landTax;
    public Tax realtyTax;
    public Tax luxuryTax;
    public Tax stateDuty;

    //methods
    public void CreateTaxes() {
        landTax = new LandTaxforPhysicalPerson(LandSquare);
        realtyTax = new RealEstateTaxforPhysicalPerson(TypeofRealty, SquareofRealty);
        luxuryTax = new LuxuryTax(CarPrice, CarAge);
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

    public void CreateStateDuty(double SellPrice) {
        stateDuty = new StateDuty(SellPrice);
        stateDuty.calculateTax();
    }

    public void SetAdditionalInf(double carPrice, int carAge, String typeofRealty,
                                 double sdquareofRealty, double landSquare) {
        CarPrice = carPrice;
        CarAge = carAge;
        TypeofRealty = typeofRealty;
        SquareofRealty = sdquareofRealty;
        LandSquare = landSquare;
        CreateTaxes();

    }

    //Constructor
    public PhysicalPerson(String login, String password,String name, String sername, int age, int birthYear, String passNumber,
                          String gender, String location, int identificationCode) {
        this.login=login;
        this.password = password;
        Name = name;
        Sername = sername;
        Age = age;
        BirthYear = birthYear;
        PassNumber = passNumber;
        Gender = gender;
        Location = location;
        IdentificationCode = identificationCode;

    }
}
