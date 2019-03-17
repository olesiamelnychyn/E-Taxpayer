package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller_Information {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button BackButton;

        @FXML
        void initialize() {

            BackButton.setOnAction(event -> {
                BackButton.getScene().getWindow().hide();
                double x=BackButton.getScene().getWindow().getX();
                double y=BackButton.getScene().getWindow().getY();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/Controllers/Home.fxml"));
                try {
                    loader.load();
                } catch ( IOException e){
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("E-Taxpayer");
                stage.setX(x);
                stage.setY(y);
                stage.setScene(new Scene(root));
                stage.show();
            });

        }
    }
