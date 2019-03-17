package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller_Home {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LogInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private Text WelcomeText;

    @FXML
    private Button InfButton;

    @FXML
    void initialize() {
        InfButton.setOnAction(event -> {
            PresedToOpen(InfButton, "Information", "Information");
        });

        LogInButton.setOnAction(event -> {
            PresedToOpen(LogInButton, "Log In", "LogIn");
        });

        SignUpButton.setOnAction(event -> {
            PresedToOpen(SignUpButton, "Sign Up", "TypeOfPerson");
        });

    }

    public void PresedToOpen(Button button, String title, String location){
        String Location = "/sample/Controllers/"+location+".fxml";
        button.getScene().getWindow().hide();
        double x=button.getScene().getWindow().getX();
        double y=button.getScene().getWindow().getY();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(Location));
        try {
            loader.load();
        } catch ( IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setX(x);
        stage.setY(y);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
