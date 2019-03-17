package sample.Controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private Text ProfileText;

    @FXML
    private Text t1;

    @FXML
    private Text t2;

    @FXML
    private Text t3;

    @FXML
    private Text t4;

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

        ArrayList<Text> TitleTax = new ArrayList<>();
        TitleTax.add(t1);
        TitleTax.add(t2);
        TitleTax.add(t3);
        TitleTax.add(t4);
        ArrayList<Text> Tax = new ArrayList<>();
        Tax.add(tax1);
        Tax.add(tax2);
        Tax.add(tax3);
        Tax.add(tax4);

        for (Text text : TitleTax){
            text.setVisible(false);
        }
        for (Text text : Tax){
            text.setVisible(false);
        }

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
        PhysicalPerson ph =  null;

        BufferedReader br = null;
        br = new BufferedReader(new FileReader("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/E-Taxpayer/E-Taxpayer_FX/src/sample/Controllers/users.txt"));
        if(br!=null) {
            String Type = br.readLine();
            String name = br.readLine();
            String sername = br.readLine();
            int age = Integer.valueOf(br.readLine());
            int birthyear = Integer.valueOf(br.readLine());
            String passnumber = br.readLine();
            String gender = br.readLine();
            String location = br.readLine();
            int identnumb = Integer.valueOf(br.readLine());

            ph = new PhysicalPerson(name, sername, age, birthyear, passnumber, gender, location, identnumb);

            Hello.setText("Hello, " + name + " " + sername + "!");
            ProfileText.setText("Name: " + name + "\n" +
                    "Sername: " + sername + "\n" +
                    "Age: " + age + "\n" +
                    "Year of birth: " + birthyear + "\n" +
                    "Passport number: " + passnumber + "\n" +
                    "Gender: " + gender + "\n" +
                    "Location: " + location + "\n" +
                    "Identification number: " + identnumb + "\n");

            PhysicalPerson finalPh = ph;
            AddInfButton.setOnAction(event -> {
                    finalPh.SetAdditionalInf(Integer.valueOf(CarPrice.getText()), Integer.valueOf(CarAge.getText()), TypeOfRealty.getText(), Integer.valueOf(SquareOfRealty.getText()), Integer.valueOf(LandSquare.getText()));
                    finalPh.CreateTaxes();
                finalPh.landTax.NewMounth();
                finalPh.realtyTax.NewMounth();
                finalPh.luxuryTax.NewMounth();
                int i=0;
                    if(!t1.isVisible()) {
                        t1.setText("Land tax: ");
                        t2.setText("Luxury tax: ");
                        t3.setText("Realty tax: ");

                        tax1.setText(String.valueOf(finalPh.landTax.CurrentBalance));
                        tax2.setText(String.valueOf(finalPh.luxuryTax.CurrentBalance));
                        tax3.setText(String.valueOf(finalPh.realtyTax.CurrentBalance));
                        i=1;
                    }
                    else{
                        t2.setText("Land tax: ");
                        t3.setText("Luxury tax: ");
                        t4.setText("Realty tax: ");

                        tax2.setText(String.valueOf(finalPh.landTax.CurrentBalance));
                        tax3.setText(String.valueOf(finalPh.luxuryTax.CurrentBalance));
                        tax4.setText(String.valueOf(finalPh.realtyTax.CurrentBalance));
                    }
                    int j=i;
                for (Text text : TitleTax) {
                    if (i <= 3) {
                        text.setVisible(true);
                        i++;
                    }
                }
                for (Text text : Tax) {
                    if (j <= 3) {
                        text.setVisible(true);
                        j++;
                    }
                }

                AddInfPane.setDisable(true);

            });

            AddDutyButton.setOnAction(event -> {
                finalPh.CreateStateDuty(Integer.valueOf(DutyPrice.getText()));
                finalPh.stateDuty.NewMounth();
                if(!t1.isVisible()) {
                    t1.setVisible(true);
                    tax1.setVisible(true);
                    t1.setText("State Duty:");
                    tax1.setText(String.valueOf(finalPh.stateDuty.CurrentBalance));
                } else {
                    t4.setVisible(true);
                    tax4.setVisible(true);
                    t4.setText("State Duty:");
                    tax4.setText(String.valueOf(finalPh.stateDuty.CurrentBalance));
                }
            });
        }

    }
}









