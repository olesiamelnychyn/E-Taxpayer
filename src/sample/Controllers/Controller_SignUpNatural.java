package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import sample.Exceptions.ExistingLogin;
import sample.Persons.PhysicalPerson;
import sample.User.Users;

/**
 * This is a controller of the page made for person-entrepreneur to register.
 */
public class Controller_SignUpNatural {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * This button adds the user to the "base" and opens {@link Controller_LogIn}.
     */
    @FXML
    private Button SignUpButton;

    /**
     * This button returns you to {@link Controller_Home}.
     */
    @FXML
    private Button BackButton;

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
     * This is a field where user have to write his/her country.
     */
    @FXML
    private TextField Country;

    /**
     * This is a field where user have to write his/her passport number.
     */
    @FXML
    private TextField PassNumber;

    /**
     * This is a field where user have to write his/her identification code.
     */
    @FXML
    private TextField IdentificationCode;

    /**
     * This is a field where user have to write his/her username.
     */
    @FXML
    private TextField Login;

    /**
     * This is a field where user have to write his/her password.
     */
    @FXML
    private TextField Password;

    /**
     * This is a field where user have to choose his/her gender.
     */
    @FXML
    private CheckBox Male;

    /**
     * This is a field where user have to choose his/her gender.
     */
    @FXML
    private CheckBox Female;

    /**
     * This method adds functionality to the buttons.
     */
    @FXML
    void initialize() {
        Male.setSelected(true);

        Male.setOnAction(event -> {
            if (Male.isSelected())
                Female.setSelected(false);
        });

        Female.setOnAction(event -> {
            if (Female.isSelected())
                Male.setSelected(false);
        });

        SignUpButton.setOnAction(event -> {
            if (Name.getText().isEmpty() || Surname.getText().isEmpty() || Age.getText().isEmpty() || Birthyear.getText().isEmpty() || Country.getText().isEmpty() || PassNumber.getText().isEmpty()
                    || IdentificationCode.getText().isEmpty() || Login.getText().isEmpty() || Password.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Problem");
                alert.setHeaderText(null);
                alert.setContentText("One or more fields are empty. To register, You have to fill all of them.");

                alert.showAndWait();
            } else if( Name.getText().matches("[a-zA-Z]+") && Surname.getText().matches("[a-zA-Z]+") && Age.getText().matches("\\d+") && Birthyear.getText().matches("\\d{4}") && IdentificationCode.getText().matches("\\d{9}") && Country.getText().matches("[a-zA-Z]+") && PassNumber.getText().matches("[A-Z]{2}[0-9]{5}[A-Z]{2}")){

                int age = Integer.valueOf(Age.getText());
                String gender;
                if (Male.isSelected())
                    gender = "Male";
                else gender = "Female";
                PhysicalPerson physicalPerson = new PhysicalPerson(Login.getText(), Password.getText(), Name.getText(), Surname.getText(), age, Integer.valueOf(Birthyear.getText()), Country.getText(), PassNumber.getText(), gender, Integer.valueOf(IdentificationCode.getText()));
                physicalPerson.SetAdditionalInf(0, 0, "-", 0, 0);
                Users ph = new Users();
                try {
                    ph.CreatePhysical();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                int i = 0;
                try {
                    ph.CheckExistingPhy(physicalPerson);
                } catch (ExistingLogin e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Problem");
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());

                    alert.showAndWait();
                    System.out.println(e.getMessage());
                    i++;
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

        BackButton.setOnAction(event -> Open.PressedToOpen(BackButton, "e-Taxpayer", "Home"));
    }
}


