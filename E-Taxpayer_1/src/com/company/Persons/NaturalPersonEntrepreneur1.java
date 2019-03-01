package com.company.Persons;

import com.company.Taxes.*;
import com.company.Taxes.TaxesNaturalPersonEntrepreneur.SingleTax;

    public class NaturalPersonEntrepreneur1 implements NaturalPerson {

        //Common Information
        public String Name;
        String Sername;
        int Years;
        int BirthYear;
        private String PassNumber;
        String Gender;
        private String Location;
        private String IdentificationCode;

        //Taxes (all taxes will be in a Collection soon)
        Tax singleTax;
        Tax singleSocialContribution;

        //For Taxes
        double CirculationOfMoney;
        double MinSalary = 4173.0;

        //Methods
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

        public String GetIdentificationCode() {
            return IdentificationCode;
        }

        //Constructor
        public NaturalPersonEntrepreneur1(String name, String sername, int years, int birthYear, String passNumber,
                                          String gender, String location, String identificationCode, double circulationOfMoney) {
            Name = name;
            Sername = sername;
            Years = years;
            BirthYear = birthYear;
            PassNumber = passNumber;
            Gender = gender;
            Location = location;
            IdentificationCode = identificationCode;
            CirculationOfMoney = circulationOfMoney;
        }
    }

