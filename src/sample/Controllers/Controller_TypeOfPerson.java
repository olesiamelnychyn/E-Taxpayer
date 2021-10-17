package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * This is a controller where person has to choose his/her/it's type.
 */
public class Controller_TypeOfPerson {

    /**
     * This button returns you to {@link Controller_Home}.
     */
    @FXML
    private Button BackButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * There user have to choose type.
     */
    @FXML
    private CheckBox Natural;

    /**
     * There user have to choose type.
     */
    @FXML
    private CheckBox Juridical;

    /**
     * There user have to choose type.
     */
    @FXML
    private CheckBox Entrepreneur;

    /**
     * This button opens next controller of registration.
     */
    @FXML
    private Button NextButton;

    /**
     * This method adds functionality.
     */
    @FXML
    void initialize() {
        Natural.setOnAction(event -> {
            if (Natural.isSelected()) {
                Juridical.setSelected(false);
                Entrepreneur.setSelected(false);
            }
        });
        Juridical.setOnAction(event -> {
            if (Juridical.isSelected()) {
                Natural.setSelected(false);
                Entrepreneur.setSelected(false);
            }
        });

        Entrepreneur.setOnAction(event -> {
            if (Entrepreneur.isSelected()) {
                Juridical.setSelected(false);
                Natural.setSelected(false);
            }
        });
        NextButton.setOnAction(event -> {
            if (Natural.isSelected()) {
                Open.PressedToOpen(NextButton, "Sign Up", "SignUpNatural");
            } else if (Juridical.isSelected()) {
                Open.PressedToOpen(NextButton, "Sign Up", "SignUpJuridical");
            } else if (Entrepreneur.isSelected()) {
                Open.PressedToOpen(NextButton, "Sign Up", "SignUpEntrepreneur");
            }
        });

        BackButton.setOnAction(event -> Open.PressedToOpen(BackButton, "E-Taxpayer", "Home"));

    }
}









