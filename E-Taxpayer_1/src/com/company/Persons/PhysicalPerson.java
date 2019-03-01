package com.company.Persons;

import com.company.Taxes.*;
import com.company.Taxes.TaxesPhysicalPerson.*;

public class PhysicalPerson implements NaturalPerson {

    //common information
    public String Name;
    String Sername;
    int Age;
    int BirthYear;
    private String PassNumber;
    String Gender;
    private String Location;
    private String IdentificationCode;

    //Luxury
    double CarPrice;
    int CarAge;

    //Realty
    String TypeofRealty = null; // flat or house
    double SquareofRealty;

    //Land
    double LandSquare;

    //Taxes
    Tax landTax;
    Tax realtyTax;
    Tax luxuryTax;

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

    public String GetIdentificationCode() {
        return IdentificationCode;
    }

    public void CreateStateDuty(double SellPrice) {
        Tax stateDuty = new StateDuty(SellPrice);
    }

    //Constructor
    public PhysicalPerson(String name, String sername, int age, int birthYear, String passNumber, String gender, String location,
                          String identificationCode, double carPrice, int carAge, String typeofRealty, double sdquareofRealty, double landSquare) {
        Name = name;
        Sername = sername;
        Age = age;
        BirthYear = birthYear;
        PassNumber = passNumber;
        Gender = gender;
        Location = location;
        IdentificationCode = identificationCode;
        CarPrice = carPrice;
        CarAge = carAge;
        TypeofRealty = typeofRealty;
        SquareofRealty = sdquareofRealty;
        LandSquare = landSquare;
    }
}
