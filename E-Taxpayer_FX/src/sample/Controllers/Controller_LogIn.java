package sample.Controllers;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;



public class Controller_LogIn  {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginText;

    @FXML
    private PasswordField PasswordText;

    @FXML
    private Button SignInButton;

    @FXML
    private Button InfoButton;

    @FXML
    private Button BackButton;

    @FXML
    private ChoiceBox<?> type;

    @FXML
    void initialize() {

        InfoButton.setOnAction(event -> {
            PresedToOpen(InfoButton, "Information", "Information");
        });

        SignInButton.setOnAction(e -> {
            String login = LoginText.getText();
            String password = PasswordText.getText();

            if(login.isEmpty() || password.isEmpty()){
                double x1 = LoginText.getLayoutX();
                double x2 = PasswordText.getLayoutX();
                double y1 = LoginText.getLayoutY();
                double y2 = PasswordText.getLayoutY();
                Shake logNode = new Shake(LoginText);
                Shake pasNode = new Shake(PasswordText);
                if (login.isEmpty()) {
                    System.out.println("NO");
                    logNode.playAnim();
                    LoginText.setLayoutX(x1);
                    LoginText.setLayoutY(y1);
                }
                if (password.isEmpty()) {
                    System.out.println("NO");
                    pasNode.playAnim();
                    PasswordText.setLayoutX(x2);
                    PasswordText.setLayoutY(y2);
                }
            } else{
                Users phuser = new Users();
                try {
                    phuser.CreatePhysical();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                String result = phuser.CheckPhysical(login, password);
               if(result!=null){
                   System.out.println("OK "+result);

                   PrintWriter pw = null;
                   try {
                       pw = new PrintWriter("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/E-Taxpayer/E-Taxpayer_FX/src/sample/Controllers/number.txt");
                   } catch (FileNotFoundException e1) {
                       e1.printStackTrace();
                   }
                   if(pw!=null){
                   pw.println(result);
                   }else System.out.println("!!!!NO!!!!!");
                   pw.close();


                   PresedToOpen(SignInButton, "Hello", "UserCabinet");
               }
            }
        });

        BackButton.setOnAction(event -> {
            PresedToOpen(BackButton, "E-Taxpayer", "Home");
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




