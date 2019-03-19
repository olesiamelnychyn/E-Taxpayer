package sample.Controllers;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Persons.PhysicalPerson;
import javafx.scene.control.Tab;

public class Controller_UserCabinet {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text t1;

    @FXML
    private Text ProfileText;

    @FXML
    private Text tax1;

    @FXML
    private Text tax2;

    @FXML
    private Text tax3;

    @FXML
    private Text tax4;

    @FXML
    private TextField CarAge;

    @FXML
    private TextField CarPrice;

    @FXML
    private TextField TypeOfRealty;

    @FXML
    private TextField SquareOfRealty;

    @FXML
    private TextField LandSquare;

    @FXML
    private Button AddInfButton;

    @FXML
    private TextField DutyPrice;

    @FXML
    private Button AddDutyButton;


    @FXML
    private Tab AddInfPane;

    @FXML
    private Button LogOutButton;

    @FXML
    private Text Hello;


    @FXML
    void initialize() throws IOException {

        t1.setVisible(false);
        tax4.setVisible(false);

        LogOutButton.setOnAction(event -> {
            LogOutButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource( "/sample/Controllers/LogIn.fxml"));
            try {
                loader.load();
            } catch ( IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Log In");
            stage.setScene(new Scene(root));
            stage.show();
        });
        BufferedReader br = null;
        final int[] Number = new int[1];
        PhysicalPerson finalPh =null;
        br = new BufferedReader(new FileReader("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/E-Taxpayer/E-Taxpayer_FX/src/sample/Controllers/number.txt"));
        if(br!=null) {
            Users pp = new Users();
            pp.CreatePhysical();
            final String[] number = {br.readLine()};
            Number[0] =Integer.valueOf(number[0]);
            finalPh = pp.ph.get(Number[0]);

            PrintWriter pw = null;
            try {
                pw = new PrintWriter("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/E-Taxpayer/E-Taxpayer_FX/src/sample/Controllers/number.txt");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            if(pw!=null){
                pw.println();}

            Hello.setText("Hello, " + finalPh.Name + " " + finalPh.Sername+ "!");
            ProfileText.setText("Name: " + finalPh.Name + "\n" +
                    "Sername: " + finalPh.Sername + "\n" +
                    "Age: " + finalPh.Age + "\n" +
                    "Year of birth: " + finalPh.BirthYear + "\n" +
                    "Passport number: " + finalPh.GetPassNumber() + "\n" +
                    "Gender: " + finalPh.Gender + "\n" +
                    "Location: " + finalPh.GetLocation()+ "\n" +
                    "Identification number: " + finalPh.GetIdentificationCode() + "\n");
            finalPh.CreateTaxes();
            finalPh.landTax.NewMounth();
            finalPh.realtyTax.NewMounth();
            finalPh.luxuryTax.NewMounth();
            tax1.setText(String.valueOf(finalPh.landTax.CurrentBalance));
            tax2.setText(String.valueOf(finalPh.luxuryTax.CurrentBalance));
            tax3.setText(String.valueOf(finalPh.realtyTax.CurrentBalance));
            CarAge.setText(String.valueOf(finalPh.CarAge));
            CarPrice.setText(String.valueOf(finalPh.CarPrice));
            TypeOfRealty.setText(finalPh.TypeofRealty);
            SquareOfRealty.setText(String.valueOf(finalPh.SquareofRealty));
            LandSquare.setText(String.valueOf(finalPh.LandSquare));

            PhysicalPerson finalPh1 = finalPh;
            AddInfButton.setOnAction(event -> {
                if(finalPh1 !=null) {
                    finalPh1.SetAdditionalInf(Double.valueOf(CarPrice.getText()), Integer.valueOf(CarAge.getText()), TypeOfRealty.getText(), Double.valueOf(SquareOfRealty.getText()), Double.valueOf(LandSquare.getText()));
                    finalPh1.CreateTaxes();
                    finalPh1.landTax.NewMounth();
                    finalPh1.realtyTax.NewMounth();
                    finalPh1.luxuryTax.NewMounth();

                        tax1.setText(String.valueOf(finalPh1.landTax.CurrentBalance));
                        tax2.setText(String.valueOf(finalPh1.luxuryTax.CurrentBalance));
                        tax3.setText(String.valueOf(finalPh1.realtyTax.CurrentBalance));
                        Number[0] =pp.ChangePhysical(Integer.valueOf(number[0]), finalPh1);
                        number[0]=String.valueOf(Number[0]);

                }
            });

            AddDutyButton.setOnAction(event -> {
                finalPh1.CreateStateDuty(Integer.valueOf(DutyPrice.getText()));
                finalPh1.stateDuty.NewMounth();
                    t1.setVisible(true);
                    tax4.setVisible(true);
                    t1.setText("State Duty:");
                    tax4.setText(String.valueOf(finalPh1.stateDuty.CurrentBalance));

            });
        }

    }
}









