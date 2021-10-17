package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Exceptions.ExistingLogin;
import sample.Persons.JuridicalPerson;
import sample.Persons.NaturalPersonEntrepreneur1;
import sample.Persons.NaturalPersonEntrepreneur2;
import sample.User.Users;

/**
 * This is a controller of the page made for entrepreneur person to register.
 */
public class Controller_SignUpEntrepreneur {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * This is a field where user have to write his/her name.
     */
    @FXML
    private TextField Name;

    /**
     * This is a field where user have to write his/her surname.
     */
    @FXML
    private TextField Surname;

    /**
     * This is a field where user have to write his/her age.
     */
    @FXML
    private TextField Age;

    /**
     * This is a field where user have to write his/her year of birth.
     */
    @FXML
    private TextField Birthyear;

    /**
     * This is a field where user have to write his/her passport number.
     */
    @FXML
    private TextField PassNumber;

    /**
     * This is a field where user have to write his/her country.
     */
    @FXML
    private TextField Location;

    /**
     * This is a field where user have to write his/her identification code.
     */
    @FXML
    private TextField IdentCode;

    /**
     * This is a field where user have to write his/her password.
     */
    @FXML
    private TextField Password;

    /**
     * This is a field where user have to write his/her login.
     */
    @FXML
    private TextField Login;

    /**
     * User have to choose what income he/she has: ">" or "<" than 5 000 000.
     */
    @FXML
    private ChoiceBox<?> IncomeChoice;

    /**
     * This button add the user to the "base" and opens {@link Controller_LogIn}.
     */
    @FXML
    private Button SignUpButton;

    /**
     * This button return the user to {@link Controller_Home}.
     */
    @FXML
    private Button BackButton;

    /**
     * User have to choose his/her gender.
     */
    @FXML
    private CheckBox male;

    /**
     * User have to choose his/her gender.
     */
    @FXML
    private CheckBox female;

    /**
     * This method  adds functionality to the buttons.
     */
    @FXML
    void initialize() {
        male.setOnAction(event -> {
            if (male.isSelected())
                female.setSelected(false);
        });

        female.setOnAction(event -> {
            if (female.isSelected())
                male.setSelected(false);
        });

        SignUpButton.setOnAction(event -> {
            if(Name.getText().isEmpty() || Surname.getText().isEmpty() || Age.getText().isEmpty() || Birthyear.getText().isEmpty() || IdentCode.getText().isEmpty() || Location.getText().isEmpty() || PassNumber.getText().isEmpty() || Login.getText().isEmpty() || Password.getText().isEmpty() || IncomeChoice.getValue().equals("Choose income")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Problem");
                alert.setHeaderText(null);
                alert.setContentText("One or more fields are empty. To register, You have to fill all of them.");

                alert.showAndWait();
            } else if (Name.getText().matches("[a-zA-Z]+") && Surname.getText().matches("[a-zA-Z]+") &&
                    Age.getText().matches("\\d+") && Birthyear.getText().matches("(19|20)\\d{2}") &&
                    IdentCode.getText().matches("\\d{9}") && Location.getText().matches("[a-zA-Z]+") &&
                    PassNumber.getText().matches("[A-Z]{2}[0-9]{5}[A-Z]{2}")) {

                Users user = new Users();
                int i = 0;
                if (IncomeChoice.getValue().equals("Income ≥ 5 000 000")) {
                    JuridicalPerson juridicalPerson = new JuridicalPerson(Login.getText(), Password.getText(),
                            Name.getText() + " " + Surname.getText(),
                            Integer.valueOf(Birthyear.getText()),
                            Location.getText(), Integer.valueOf(IdentCode.getText()));
                    juridicalPerson.SetAdditionalInf(0, 0, false, false, 0, 0, 0, false, 0, 0);
                    NaturalPersonEntrepreneur2 ent2 = new NaturalPersonEntrepreneur2(juridicalPerson);

                    try {
                        user.CreateEntrepreneur2();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        user.CheckExistingEnt2(ent2);
                    } catch (ExistingLogin e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Problem");
                        alert.setHeaderText(null);
                        alert.setContentText(e.getMessage());

                        alert.showAndWait();
                        System.out.println(e.getMessage());
                        i++;
                    }


                } else {

                    String Gender = "Male";
                    if (female.isSelected()) {
                        Gender = "Female";
                    }

                    NaturalPersonEntrepreneur1 entrepreneur1 = new NaturalPersonEntrepreneur1(Login.getText(), Password.getText(), Name.getText(),
                            Surname.getText(), Integer.valueOf(Age.getText()),
                            Integer.valueOf(Birthyear.getText()), PassNumber.getText(),
                            Gender, Location.getText(), Integer.valueOf(IdentCode.getText()));

                    entrepreneur1.SetAdditionalInf(0);

                    try {
                        user.CreateEntrepreneur();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        user.CheckExistingEnt(entrepreneur1);
                    } catch (ExistingLogin e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Problem");
                        alert.setHeaderText(null);
                        alert.setContentText(e.getMessage());

                        alert.showAndWait();
                        System.out.println(e.getMessage());
                        i++;
                    }

                }
                if (i == 0) {
                    Open.PressedToOpen(SignUpButton, "Log In", "LogIn");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Problem");
                alert.setHeaderText(null);
                alert.setContentText("It is written unexpected character.");

                alert.showAndWait();
            }
        });

        BackButton.setOnAction(event -> Open.PressedToOpen(BackButton, "E-Taxpayer", "Home"));
    }
}