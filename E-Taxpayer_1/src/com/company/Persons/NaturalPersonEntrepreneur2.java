package com.company.Persons;

public class NaturalPersonEntrepreneur2 implements NaturalPerson {

    private JuridicalPerson juridicalPerson;
    public String Name = juridicalPerson.Title;
    int BirthYear = juridicalPerson.BirthYear;
    private String Location = juridicalPerson.GetLocation();
    private int IdentificationCode = juridicalPerson.GetOSREOUCode();

    public void CreateTaxes() {
        juridicalPerson.CreateTaxes();
    }

    public String GetLocation() {
        return Location;
    }

    public int GetIdentificationCode() {
        return IdentificationCode;
    }

    //Constructor
    public NaturalPersonEntrepreneur2(JuridicalPerson juridicalPerson) {
        this.juridicalPerson = juridicalPerson;
    }

}
