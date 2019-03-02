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
    private int IdentificationCode;

    //Luxury
    double CarPrice;
    int CarAge;

    //Realty
    String TypeofRealty = null; // flat or house
    double SquareofRealty;

    //Land
    double LandSquare;

    //Taxes (all taxes will be in a Collection soon)
    Tax landTax;
    Tax realtyTax;
    Tax luxuryTax;
    Tax stateDuty;

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
    public PhysicalPerson(String name, String sername, int age, int birthYear, String passNumber,
                          String gender, String location, int identificationCode) {
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
