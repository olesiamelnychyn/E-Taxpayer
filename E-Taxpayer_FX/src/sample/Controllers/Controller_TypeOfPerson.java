package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import sample.Controllers.Shake;

import javax.swing.*;

public class Controller_TypeOfPerson {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox Natural;

    @FXML
    private CheckBox Juridical;

    @FXML
    private CheckBox Entrepreneur;

    @FXML
    private Button NextButton;

    @FXML
    void initialize() {
       NextButton.setOnAction(event -> {
           int i=0;
           if(Natural.isSelected()){
               i++;
           }
           if(Juridical.isSelected()){
               i++;
           }
           if (Entrepreneur.isSelected()){
               i++;
           }
           if(i!=1){
               Shake nat = new Shake(Natural);
               Shake jur = new Shake(Juridical);
               Shake ent = new Shake(Entrepreneur);
               nat.playAnim();
               jur.playAnim();
               ent.playAnim();
           } else{
               System.out.println("OK");
               NextButton.getScene().getWindow().hide();
               double x=NextButton.getScene().getWindow().getX();
               double y=NextButton.getScene().getWindow().getY();
               FXMLLoader loader = new FXMLLoader();
                   loader.setLocation(getClass().getResource("/sample/Controllers/SignUpNatural.fxml"));
                   try {
                       loader.load();
                   } catch ( IOException e){
                       e.printStackTrace();
                   }

                   Parent root = loader.getRoot();

                   Stage stage = new Stage();
                   stage.setTitle("Of");
                   stage.setX(x);
                   stage.setY(y);
                   stage.setScene(new Scene(root));
                   stage.show();

               }
       });
    }
}


