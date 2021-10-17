package sample.Controllers;

import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Animation.Shake;
import sample.User.Users;

/**
 * This cis a controller which runs the process of signing in.
 */
public class Controller_LogIn {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * This is a textfield, where user have to write his/her login.
     */
    @FXML
    private TextField LoginText;

    /**
     * This is a textfield, where user have to write his/her password.
     */
    @FXML
    private PasswordField PasswordText;

    /**
     * This button check if there exist any user of chosen type with written login and password. If yes, then it open {@link Controller_UserCabinet_PH}, {@link Controller_UserCabinet_Ent} or {@link Controller_UserCabinet_JR} depending to the chosen type.
     */
    @FXML
    private Button SignInButton;

    /**
     * It is the main method of controller, which adds functionality to buttons.
     */
    @FXML
    private Button InfoButton;

    /**
     * This button return you to {@link Controller_Home}.
     */
    @FXML
    private Button BackButton;

    /**
     * There user have to chose what type of person he/she is.
     */
    @FXML
    private ChoiceBox<?> type;

    /**
     * It is the main method of controller, which adds functionality to buttons.
     */
    @FXML
    void initialize() {
        InfoButton.setOnAction(event -> Open.PressedToOpen(InfoButton, "Information", "Information"));

        SignInButton.setOnAction(e -> {
            int i=0;
            String login = LoginText.getText();
            String password = PasswordText.getText();

            if (login.isEmpty() || password.isEmpty()) {
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
            } else if (login.equals("admin") && password.equals("admin")) {
                Open.PressedToOpen(SignInButton, "Administrator", "AdminPage");
            } else {
                Users user = new Users();

                if (type.getValue().equals("Natural person")) {
                    try {
                        user.CreatePhysical();
                    } catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    String result = user.CheckPhysical(login, password);
                    if(result==null)
                        i++;
                    Print(result, "UserCabinetPH", false, null);


                } else if (type.getValue().equals("Juridical person")) {
                    try {
                        user.CreateJuridical();
                    } catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    String result = user.CheckJuridical(login, password);
                    Print(result, "UserCabinetJR", true, "jr");

                    if(result==null)
                        i++;
                } else if (type.getValue().equals("Natural person-entrepreneur")) {
                    try {
                        user.CreateEntrepreneur();
                    } catch (IOException | ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    String result = user.CheckEntrepreneur(login, password);

                    if (result != null) {
                        Print(result, "UserCabinetEnt", false, null);
                    } else if (result == null) {
                        try {
                            user.CreateEntrepreneur2();
                        } catch (IOException | ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        result = user.CheckEntrepreneur2(login, password);
                        Print(result, "UserCabinetJR", true, "ent");
                    }

                    if(result==null)
                        i++;

                }
                System.out.println(i);
                if(i > 0 ) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Problem");
                    alert.setHeaderText(null);
                    alert.setContentText("Login, password or type of person is wrong.");

                    alert.showAndWait();
                    i=0;
                }

            }


        });

        BackButton.setOnAction(event -> Open.PressedToOpen(BackButton, "E-Taxpayer", "Home"));
    }

    /**
     * This method is made to write an index of the person whose information next controller has to show.
     *
     * @param result   is an index.
     * @param location name of the .fxml of the next controller.
     * @param type     is made to determine if we have to write {@link #Print#typ}.
     * @param typ      is made in case it is {@link sample.Persons.JuridicalPerson} or {@link sample.Persons.NaturalPersonEntrepreneur2}, whose information are shown by one controller
     */
    private void Print(String result, String location, boolean type, String typ) {
        if (result != null) {
            System.out.println("OK " + result);

            PrintWriter pw = null;
            try {
                pw = new PrintWriter("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/number.txt");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            if (pw != null) {
                if (type) {
                    pw.println(typ);
                }
                pw.print(result);
                pw.close();
            } else System.out.println("!!!!NO!!!!!");

            Open.PressedToOpen(SignInButton, "Hello", location);

        }
    }
}




