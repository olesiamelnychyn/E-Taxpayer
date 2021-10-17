package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * This is a controller of a page, which provides the information about the app.
 */
public class Controller_Information {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * This button returns you to the {@link Controller_Home}.
     */
    @FXML
    private Button BackButton;

    /**
     * It is the main method of controller, which adds functionality to buttons.
     */
    @FXML
    void initialize() {

        BackButton.setOnAction(event -> Open.PressedToOpen(BackButton, "E-Taxpayer", "Home"));

    }
}
