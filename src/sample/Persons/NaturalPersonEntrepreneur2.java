package sample.Persons;

import java.io.Serializable;

/**
 * It is a class which represents a user - natural person-entrepreneur of type 2.<br>
 * It contains some common information like {@link NaturalPersonEntrepreneur2#Name},  {@link NaturalPersonEntrepreneur2#Location},
 * {@link NaturalPersonEntrepreneur2#BirthYear}, {@link NaturalPersonEntrepreneur2#IdentificationCode}.<br>
 * It also has all essential information about the user, on which all the taxes are based, and its taxes.<br>
 * And as it's an application, the class contains also user's {@link NaturalPersonEntrepreneur2#login} and {@link NaturalPersonEntrepreneur2#password} for access control.
 *<br>
 * This class also represents Adapter pattern.
 *
 */
public class NaturalPersonEntrepreneur2 implements Serializable {

    /**
     * This class has the same taxes as a {@link JuridicalPerson} does, so that is why Adapter pattern is used.
     *
     * @see NaturalPersonEntrepreneur2
     * @see sample.Taxes.Tax
     */
    public JuridicalPerson juridicalPerson;
    public String Name;
    public String login;
    public String password;
    int BirthYear;
    private String Location;
    private int IdentificationCode;

    //Constructor
    /**
     * This is a constructor which provides a common information about the user to work with it in the future.
     *
     * @param juridicalPerson it is the only thing which is needed why creating a {@link NaturalPersonEntrepreneur2}, because while signing in we at first create a
     *                        {@link JuridicalPerson}, and then send it to {@link NaturalPersonEntrepreneur2}.
     */
    public NaturalPersonEntrepreneur2(JuridicalPerson juridicalPerson) {
        this.juridicalPerson = juridicalPerson;
        Name = juridicalPerson.Title;
        login = juridicalPerson.login;
        password = juridicalPerson.password;
        BirthYear = juridicalPerson.BirthYear;
        login = juridicalPerson.GetLocation();
        Location = juridicalPerson.GetLocation();
        IdentificationCode = juridicalPerson.GetOSREOUCode();
    }

    public String GetLocation() {
        return Location;
    }

    public int GetIdentificationCode() {
        return IdentificationCode;
    }

    /**
     * This method creates taxes through {@link JuridicalPerson}.
     *
     * @see NaturalPersonEntrepreneur2
     * @see sample.Taxes.Tax
     */
    public void CreateTaxes() {
        juridicalPerson.CreateTaxes();
    }
}
