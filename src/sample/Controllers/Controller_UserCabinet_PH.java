package sample.Controllers;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Persons.PhysicalPerson;
import sample.Taxes.Transaction;
import sample.User.Users;

public class Controller_UserCabinet_PH {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text t1;

    /**
     * It shows your personal data.
     */
    @FXML
    private Text ProfileText;

    /**
     * This Text show the sum, which must be paid for {@link PhysicalPerson#landTax}
     */
    @FXML
    private Text tax1;

    /**
     * This Text show the sum, which must be paid for {@link PhysicalPerson#luxuryTax}
     */
    @FXML
    private Text tax2;

    /**
     * This Text show the sum, which must be paid for {@link PhysicalPerson#realtyTax}
     */
    @FXML
    private Text tax3;

    /**
     * This Text show the sum, which must be paid for {@link PhysicalPerson#stateDuty}
     */
    @FXML
    private Text tax4;

    /**
     * This is a field, where user have to enter age of his/her car, if there is one.
     */
    @FXML
    private TextField CarAge;

    /**
     * This is a field, where user have to enter price of his/her car, if there is one.
     */
    @FXML
    private TextField CarPrice;

    /**
     * This is a field, where user have to enter type of his/her realty ("flat" or "house").
     */
    @FXML
    private TextField TypeOfRealty;

    /**
     * This is a field, where user have to enter square of his/her realty.
     */
    @FXML
    private TextField SquareOfRealty;

    /**
     * This is a field, where user have to enter square of his/her land, if there exist one.
     */
    @FXML
    private TextField LandSquare;

    /**
     * This button saves changes (setting additional information about the business).
     */
    @FXML
    private Button AddInfButton;

    /**
     * The cost of the goods, which cause state duty.
     */
    @FXML
    private TextField DutyPrice;

    /**
     * This button creates State Duty, based on information in {@link #DutyPrice}.
     */
    @FXML
    private Button AddDutyButton;

    @FXML
    private Tab AddInfPane;

    /**
     * There user have to choose for what tax he would like to pay for.
     */
    @FXML
    private ChoiceBox<?> TypeofTax1;

    /**
     * There user have to write how much he would like to pay for the tax.
     */
    @FXML
    private Spinner<Integer> Amount;

    /**
     * This button creates a transaction of payment.
     */
    @FXML
    private Button Pay;

    /**
     * Any further information, user wants to add.
     */
    @FXML
    private TextField Comment;

    /**
     * Thia button returns you to {@link Controller_LogIn}.
     */
    @FXML
    private Button LogOutButton;

    @FXML
    private Text Hello;

    /**
     * There all approved transactions are shown.
     */
    @FXML
    private ListView ListTransactions;

    /**
     * This button open a new window, which shows information about selected transaction in {@link #ListTransactions}.
     */
    @FXML
    private Button Details;

    /**
     * This method adds functionality.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void initialize() throws IOException, ClassNotFoundException {

        BufferedReader br = null;
        final int[] Number = new int[1];
        PhysicalPerson finalPh = null;
        br = new BufferedReader(new FileReader("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/number.txt"));
        if (br != null) {
            Users pp = new Users();
            pp.CreatePhysical();
            final String[] number = {br.readLine()};
            Number[0] = Integer.valueOf(number[0]);
            finalPh = pp.ph.get(Number[0]);

            Clear();
            SetBasicInf(finalPh);

            PhysicalPerson finalPh1 = finalPh;

            AddInfButton.setOnAction(event -> {
                if (finalPh1 != null) {
                    if(CarPrice.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})") && CarAge.getText().matches("\\d{1,2}") && TypeOfRealty.getText().matches("-|flat|house") && SquareOfRealty.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})") && LandSquare.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})")) {
                        finalPh1.SetAdditionalInf(Double.valueOf(CarPrice.getText()), Integer.valueOf(CarAge.getText()), TypeOfRealty.getText(), Double.valueOf(SquareOfRealty.getText()), Double.valueOf(LandSquare.getText()));
                        finalPh1.landTax.NewCalculate();
                        finalPh1.realtyTax.NewCalculate();
                        finalPh1.luxuryTax.NewCalculate();

                        tax1.setText(String.valueOf(finalPh1.landTax.CurrentBalance));
                        tax2.setText(String.valueOf(finalPh1.luxuryTax.CurrentBalance));
                        tax3.setText(String.valueOf(finalPh1.realtyTax.CurrentBalance));

                        Number[0] = ResaveinBase(pp, Number[0], finalPh1);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Problem");
                        alert.setHeaderText(null);
                        alert.setContentText("It is written unexpected character.");

                        alert.showAndWait();
                    }
                }
            });

            AddDutyButton.setOnAction(event -> {
                if(DutyPrice.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})")) {
                    finalPh1.CreateStateDuty(Integer.valueOf(DutyPrice.getText()));
                    finalPh1.stateDuty.NewCalculate();
                    t1.setVisible(true);
                    tax4.setVisible(true);
                    t1.setText("State Duty:");
                    tax4.setText(String.valueOf(finalPh1.stateDuty.CurrentBalance));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Problem");
                    alert.setHeaderText(null);
                    alert.setContentText("It is written unexpected character.");

                    alert.showAndWait();
                }
            });

            Pay.setOnAction(event -> {
                int i = 0;
                String type = (String) TypeofTax1.getValue();
                if (finalPh1 != null) {
                    Transaction transaction = null;
                    switch (type) {
                        case "Land Tax":
                            if (finalPh1.landTax == null) {
                                i++;
                            } else {
                                transaction = new Transaction("Land Tax", new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalPh1.landTax);
                            }
                            break;
                        case "Realty Tax":
                            if (finalPh1.realtyTax == null) {
                                i++;
                            } else {
                                transaction = new Transaction("Realty Tax", new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalPh1.realtyTax);
                            }
                            break;
                        case "Luxury Tax":
                            if (finalPh1.luxuryTax == null) {
                                i++;
                            } else {
                                transaction = new Transaction("Luxury Tax", new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalPh1.luxuryTax);
                            }
                            break;
                        case "State Duty":
                            if (finalPh1.stateDuty == null) {
                                i++;
                            } else {
                                transaction = new Transaction("State Duty", new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalPh1.stateDuty);
                            }
                            break;
                    }
                    if (i == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Problem");
                        alert.setHeaderText(null);
                        alert.setContentText("You haven't entered additional information for this tax, so there is nothing to pay. This transaction cannot be finished.");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText(null);
                        alert.setContentText("The transaction have been carried out successfully! It will appear in your history after being approved by administrator.");
                        alert.showAndWait();
                        finalPh1.transactions.add(transaction);
                        ResaveinBase(pp, Number[0], finalPh1);
                    }
                }

            });

            Details.setOnAction(event -> {
                int i = ListTransactions.getSelectionModel().getSelectedIndex();
                Transaction tr = finalPh1.transactions.get(i);

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Open.class.getResource("/sample/Views/Transaction.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setTitle("Transaction");
                stage.setScene(new Scene(root));
                Controller_Transaction controller = loader.getController();
                controller.initialize(tr);
                stage.show();
            });

            LogOutButton.setOnAction(event -> Open.PressedToOpen(LogOutButton, "Log In", "LogIn"));
        }

    }

    /**
     * This methods sets data, which cannot be changed while working in userpage.
     *
     * @param finalPh
     */
    private void SetBasicInf(PhysicalPerson finalPh) {

        t1.setVisible(false);
        tax4.setVisible(false);

        Hello.setText("Hello, " + finalPh.Name + " " + finalPh.Surname + "!");
        ProfileText.setText("Name: " + finalPh.Name + "\n" +
                "Sername: " + finalPh.Surname + "\n" +
                "Age: " + finalPh.Age + "\n" +
                "Year of birth: " + finalPh.BirthYear + "\n" +
                "Passport number: " + finalPh.GetPassNumber() + "\n" +
                "Gender: " + finalPh.Gender + "\n" +
                "Location: " + finalPh.GetLocation() + "\n" +
                "Identification number: " + finalPh.GetIdentificationCode() + "\n");

        tax1.setText(String.valueOf(finalPh.landTax.CurrentBalance));
        tax2.setText(String.valueOf(finalPh.luxuryTax.CurrentBalance));
        tax3.setText(String.valueOf(finalPh.realtyTax.CurrentBalance));
        CarAge.setText(String.valueOf(finalPh.CarAge));
        String carprice = String.format("%.2f", finalPh.CarPrice).replace(",",".");
        CarPrice.setText(String.valueOf(carprice));
        TypeOfRealty.setText(finalPh.TypeofRealty);
        SquareOfRealty.setText(String.valueOf(finalPh.SquareofRealty));
        LandSquare.setText(String.valueOf(finalPh.LandSquare));
        ListTransactions.getItems().removeAll();
        for (Transaction transaction1 : finalPh.transactions
        ) {
            if (transaction1.Approved) {
                ListTransactions.getItems().add(transaction1.Title + "   " + transaction1.Date);
            }
        }
    }

    /**
     * This methods saves changed person in the "base".
     *
     * @param pp             - "base"
     * @param number         - index before saving
     * @param physicalPerson - person
     * @return - index after saving
     */
    private int ResaveinBase(Users pp, int number, PhysicalPerson physicalPerson) {
        int Number = 0;
        try {
            System.out.println(number);
            Number = pp.AddorChangePhysical(number, physicalPerson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Number;
    }

    /**
     * This methods cleans the file where the index was written.
     */
    private void Clear() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/number.txt");
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        if (pw != null) {
            pw.println();
        }
    }
}









