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
import sample.Persons.NaturalPersonEntrepreneur1;
import sample.Taxes.Transaction;
import sample.User.Users;

/**
 * This is controller of the {@link NaturalPersonEntrepreneur1} userpage.
 */
public class Controller_UserCabinet_Ent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * Thia button returns you to {@link Controller_LogIn}.
     */
    @FXML
    private Button LogOutButton;

    /**
     * It shows your personal data.
     */
    @FXML
    private Text ProfileText;

    /**
     * It shows the {@link sample.Taxes.TaxesNaturalPersonEntrepreneur.SingleTax#CurrentBalance}
     */
    @FXML
    private Text SingleTax;

    /**
     * It shows {@link sample.Taxes.SingleSocialContribution#CurrentBalance}
     */
    @FXML
    private Text SingleSocialContribution;

    /**
     * It a field where user have to write circulation of money in his/her business.
     */
    @FXML
    private TextField CirculationofMoney;

    /**
     * This button saves {@link NaturalPersonEntrepreneur1#CirculationOfMoney}.
     */
    @FXML
    private Button ConfirmButton;

    @FXML
    private Text HelloText;

    /**
     * This button creates a transaction of payment.
     */
    @FXML
    private Button Pay;

    /**
     * There user have to choose for what tax he would like to pay for.
     */
    @FXML
    private ChoiceBox<?> TypeofTax;

    /**
     * There user have to write how much he would like to pay for the tax.
     */
    @FXML
    private Spinner<Integer> Amount;

    /**
     * Any further information, user wants to add.
     */
    @FXML
    private TextField Comment;

    /**
     * There all approved transactions are shown.
     */
    @FXML
    private ListView<String> ListTransactions;

    /**
     * This button open a new window, which shows information about selected transaction in {@link #ListTransactions}.
     */
    @FXML
    private Button Details;

    /**
     * This method adds functionality.
     *
     * @throws IOException
     */
    @FXML
    void initialize() throws IOException {
        LogOutButton.setOnAction(event -> Open.PressedToOpen(LogOutButton, "Log In", "LogIn"));

        NaturalPersonEntrepreneur1 finalEnt = null;
        final int[] Number = new int[1];
        Users ent = new Users();

        Number[0] = GetNumber(ent);
        finalEnt = ent.entr1.get(Number[0]);
        SetBasicInf(finalEnt);

        NaturalPersonEntrepreneur1 finalEnt1 = finalEnt;
        ConfirmButton.setOnAction(event -> {
            if (CirculationofMoney.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})")) {
                finalEnt1.SetAdditionalInf(Double.valueOf(CirculationofMoney.getText()));
                Number[0] = ResaveinBase(ent, Number[0], finalEnt1);
                update(finalEnt1);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Problem");
                alert.setHeaderText(null);
                alert.setContentText("It is unexpected character or nothing.");

                alert.showAndWait();
            }

        });

        Pay.setOnAction(event -> {
            int i = 0;
            Transaction transaction = null;
            String type = (String) TypeofTax.getValue();
            if (type.equals("Single Tax")) {
                if (finalEnt1.singleTax == null) {
                    i++;
                } else {
                    transaction = new Transaction(type, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalEnt1.singleTax);
                }
            } else if (type.equals("Single Social Contribution")) {
                if (finalEnt1.singleSocialContribution == null) {
                    i++;
                } else {
                    transaction = new Transaction(type, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalEnt1.singleSocialContribution);
                }
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
                finalEnt1.transactions.add(transaction);
                Number[0] = ResaveinBase(ent, Number[0], finalEnt1);
            }
        });

        Details.setOnAction(event -> {
            int i = ListTransactions.getSelectionModel().getSelectedIndex();
            Transaction tr = finalEnt1.transactions.get(i);

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
    }

    /**
     * This method sets data, which can be changed during work in userpage.
     *
     * @param finalEnt
     */
    private void update(NaturalPersonEntrepreneur1 finalEnt) {
        if (finalEnt.singleTax == null || finalEnt.singleSocialContribution == null) {
            finalEnt.CreateTaxes();
            finalEnt.singleTax.calculateTax();
            finalEnt.singleSocialContribution.calculateTax();
        }
        finalEnt.singleTax.NewCalculate();
        finalEnt.singleSocialContribution.NewCalculate();
        SingleTax.setText(String.valueOf(finalEnt.singleTax.CurrentBalance));
        SingleSocialContribution.setText(String.valueOf(finalEnt.singleSocialContribution.CurrentBalance));
        CirculationofMoney.setText(String.valueOf(finalEnt.CirculationOfMoney));
    }

    /**
     * This method reads the index of the person in the {@link Users#entr1} to show his/her information.
     *
     * @param ent - arraylist
     * @return - index
     * @throws IOException
     */
    private int GetNumber(Users ent) throws IOException {
        BufferedReader br = null;
        final int[] Number = new int[1];

        br = new BufferedReader(new FileReader("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/number.txt"));
        if (br != null) {

            final String[] number = {br.readLine()};
            Number[0] = Integer.valueOf(number[0]);
            try {
                ent.CreateEntrepreneur();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Clear();
        return Number[0];
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

    /**
     * This methods sets data, which cannot be changed while working in userpage.
     *
     * @param finalEnt
     */
    private void SetBasicInf(NaturalPersonEntrepreneur1 finalEnt) {
        HelloText.setText("Hello, " + finalEnt.Name + " " + finalEnt.Surname + "!");
        ProfileText.setText("Name: " + finalEnt.Name + "\n" +
                "Sername: " + finalEnt.Surname + "\n" +
                "Year of birth: " + finalEnt.BirthYear + "\n" +
                "Age: " + finalEnt.Years + "\n" +
                "Gender: " + finalEnt.Gender + "\n" +
                "Passport number: " + finalEnt.GetPassNumber() + "\n" +
                "Location: " + finalEnt.GetLocation() + "\n" +
                "Identification number: " + finalEnt.GetIdentificationCode());
        for (Transaction tr : finalEnt.transactions) {
            if (tr.Approved) {
                ListTransactions.getItems().add(tr.Title + "   " + tr.Date);
            }
        }
        finalEnt.singleTax.NewCalculate();
        finalEnt.singleSocialContribution.NewCalculate();
        SingleTax.setText(String.valueOf(finalEnt.singleTax.CurrentBalance));
        SingleSocialContribution.setText(String.valueOf(finalEnt.singleSocialContribution.CurrentBalance));
        CirculationofMoney.setText(String.valueOf(finalEnt.CirculationOfMoney));
    }

    /**
     * This methods saves changed person in the "base".
     *
     * @param pp                         - "base"
     * @param number                     - index before saving
     * @param naturalPersonEntrepreneur1 - person
     * @return - index after saving
     */
    private int ResaveinBase(Users pp, int number, NaturalPersonEntrepreneur1 naturalPersonEntrepreneur1) {
        int Number = 0;
        try {
            Number = pp.AddorChangeEntrepreneur(number, naturalPersonEntrepreneur1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Number;
    }

}

