package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.jdi.IntegerValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import sample.Persons.JuridicalPerson;
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
            || IdentificationCode.getText().isEmpty() || Login.getText().isEmpty() || PassNumber.getText().isEmpty()){
                System.out.println("No");
            } else{
                int age = Integer.valueOf(Age.getText());
                String gender;
                if(Male.isSelected())
                    gender="Male";
                else gender="Female";
                PhysicalPerson physicalPerson = new PhysicalPerson(Name.getText(), Sername.getStyle(), age,  Integer.valueOf(Birthyear.getText()), Country.getText(), PassNumber.getText(), gender, Integer.valueOf(IdentificationCode.getText()));
                System.out.println("yes");
                System.out.println(physicalPerson.GetIdentificationCode());
            }
        });
    }
}


