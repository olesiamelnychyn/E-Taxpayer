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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Persons.PhysicalPerson;

public class Controller_SignUpNatural {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField Name;

    @FXML
    private TextField Sername;

    @FXML
    private TextField Age;

    @FXML
    private TextField Birthyear;

    @FXML
    private TextField Country;

    @FXML
    private TextField PassNumber;

    @FXML
    private TextField IdentificationCode;

    @FXML
    private TextField Login;

    @FXML
    private TextField Password;

    @FXML
    private CheckBox Male;

    @FXML
    private CheckBox Female;

    @FXML
    void initialize() {
        SignUpButton.setOnAction(event -> {
            if (((Male.isSelected() && Female.isSelected()) || (!Male.isSelected() && !Female.isSelected()))){
                System.out.println("No");
            } else if (Name.getText().isEmpty() || Sername.getText().isEmpty() || Age.getText().isEmpty() || Birthyear.getText().isEmpty() || Country.getText().isEmpty() || PassNumber.getText().isEmpty()
            || IdentificationCode.getText().isEmpty() || Login.getText().isEmpty() || Password.getText().isEmpty()){
                System.out.println("No");
            } else{
                int age = Integer.valueOf(Age.getText());
                String gender;
                if(Male.isSelected())
                    gender="Male";
                else gender="Female";
                PhysicalPerson physicalPerson = new PhysicalPerson(Login.getText(), Password.getText(), Name.getText(), Sername.getText(), age,  Integer.valueOf(Birthyear.getText()), Country.getText(), PassNumber.getText(), gender, Integer.valueOf(IdentificationCode.getText()));
                physicalPerson.SetAdditionalInf(0,0,null,0,0);
                Users ph = new Users();
                try {
                    ph.CreatePhysical();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ph.AddorChangePhysical(1000, physicalPerson);
            }

            SignUpButton.getScene().getWindow().hide();
            double x=SignUpButton.getScene().getWindow().getX();
            double y=SignUpButton.getScene().getWindow().getY();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/Controllers/LogIn.fxml"));
            try {
                loader.load();
            } catch ( IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Log In");
            stage.setX(x);
            stage.setY(y);
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}


