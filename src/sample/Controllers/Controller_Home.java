package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * This is a controller of the first window which is open while running the application. There you can decide what want to do: sign up, log in or see the description of the app.
 */
public class Controller_Home {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * This button opens {@link Controller_LogIn}, where you have to write your password and login to get to your own page.
     */
    @FXML
    private Button LogInButton;

    /**
     * Thia button opens {@link Controller_TypeOfPerson}, where you have to choose what type of person you are for further signing up.
     */
    @FXML
    private Button SignUpButton;

    @FXML
    private Text WelcomeText;

    /**
     * This button opens {@link Controller_TypeOfPerson}, which shows information about the application.
     */
    @FXML
    private Button InfButton;

    /**
     * It is the main method of controller, which adds functionality to buttons.
     */
    @FXML
    void initialize() {
        InfButton.setOnAction(event -> Open.PressedToOpen(InfButton, "Information", "Information"));

        LogInButton.setOnAction(event -> Open.PressedToOpen(LogInButton, "Log In", "LogIn"));

        SignUpButton.setOnAction(event -> Open.PressedToOpen(SignUpButton, "Sign Up", "TypeOfPerson"));

    }
}
