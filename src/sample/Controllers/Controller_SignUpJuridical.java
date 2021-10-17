package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Exceptions.ExistingLogin;
import sample.Persons.JuridicalPerson;
import sample.User.Users;

/**
 * This is a controller of the page made for juridical person to register.
 */
public class Controller_SignUpJuridical {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * This is a field where user have to write it's OSREOU-code.
     */
    @FXML
    private TextField osreoucode;

    /**
     * This is a field where user have to write it's country.
     */
    @FXML
    private TextField Location;

    /**
     * This is a field where user have to write it's username.
     */
    @FXML
    private TextField login;

    /**
     * This is a field where user have to write it's password.
     */
    @FXML
    private TextField password;

    /**
     * This is a field where user have to write it's year of initiation.
     */
    @FXML
    private TextField Birthyear;

    /**
     * This button add the user to the "base" and opens {@link Controller_LogIn}
     */
    @FXML
    private Button SignUpButton;

    /**
     * This is a field where user have to write it's title.
     */
    @FXML
    private TextField title;

    /**
     * This is a button which returns to {@link Controller_Home}.
     */
    @FXML
    private Button BackButton;

    /**
     * This method adds functionality buttons.
     */
    @FXML
    void initialize() {
        SignUpButton.setOnAction(event -> {
            if (title.getText().isEmpty() || Birthyear.getText().isEmpty() || Location.getText().isEmpty() || osreoucode.getText().isEmpty() || login.getText().isEmpty() || password.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Problem");
                alert.setHeaderText(null);
                alert.setContentText("One or more fields are empty. To register, You have to fill all of them.");

                alert.showAndWait();
            } else if( Birthyear.getText().matches("\\d{4}") && osreoucode.getText().matches("\\d{9}") && Location.getText().matches("[a-zA-Z]+")){
                JuridicalPerson juridicalPerson = new JuridicalPerson(login.getText(), password.getText(), title.getText(), Integer.valueOf(Birthyear.getText()), Location.getText(), Integer.valueOf(osreoucode.getText()));
                juridicalPerson.SetAdditionalInf(0, 0, false, false, 0, 0, 0, false, 0, 0);
                Users jr = new Users();
                try {
                    jr.CreateJuridical();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                int i = 0;
                try {
                    jr.CheckExistingJur(juridicalPerson);
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

        BackButton.setOnAction(event -> Open.PressedToOpen(BackButton, "E-Taxpayer", "Home"));
    }
}
