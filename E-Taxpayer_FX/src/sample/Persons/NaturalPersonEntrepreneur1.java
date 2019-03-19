package sample.Persons;

import sample.Taxes.*;
import sample.Taxes.TaxesNaturalPersonEntrepreneur.SingleTax;

    public class NaturalPersonEntrepreneur1 implements NaturalPerson {

        //Common Information
        public String login;
        public String password;
        public String Name;
        String Sername;
        int Years;
        int BirthYear;
        private String PassNumber;
        String Gender;
        private String Location;
        private int IdentificationCode;

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

        public int GetIdentificationCode() {
            return IdentificationCode;
        }

        public void SetAdditionalInf(double circulationOfMoney) {
            CirculationOfMoney = circulationOfMoney;
            CreateTaxes();
        }

        //Constructor
        public NaturalPersonEntrepreneur1(String name, String sername, int years, int birthYear, String passNumber,
                                          String gender, String location, int identificationCode) {
            Name = name;
            Sername = sername;
            Years = years;
            BirthYear = birthYear;
            PassNumber = passNumber;
            Gender = gender;
            Location = location;
            IdentificationCode = identificationCode;
        }
    }

