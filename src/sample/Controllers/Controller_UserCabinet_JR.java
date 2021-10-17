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
import sample.Persons.JuridicalPerson;
import sample.Persons.NaturalPersonEntrepreneur2;
import sample.Taxes.SingleSocialContribution;
import sample.Taxes.StateDuty;
import sample.Taxes.Tax;
import sample.Taxes.TaxesJuridicalPerson.*;
import sample.Taxes.Transaction;
import sample.User.Users;

/**
 * This is controller of the {@link NaturalPersonEntrepreneur2} or {@link JuridicalPerson} userpage.
 */
public class Controller_UserCabinet_JR {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
     * It shows your personal data.
     */
    @FXML
    private Text Profile;

    /**
     * It is shown if user has touristic business.
     */
    @FXML
    private Text TouristicText;

    /**
     * It is shown if there exist {@link StateDuty}.
     */
    @FXML
    private Text StateDutyText;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#VAT}
     */
    @FXML
    private Text VATax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#SingleSocialContribution}
     */
    @FXML
    private Text SingleSocialContributionTax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#IncomeTax}
     */
    @FXML
    private Text IncomeTax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#MineralResourcesTax}
     */
    @FXML
    private Text MineralResourcesTax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#EcologicalTax}
     */
    @FXML
    private Text EcologicalTax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#IncomeNatPerTax}
     */
    @FXML
    private Text IncomeNatPerTax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#MilitaryTax}
     */
    @FXML
    private Text MilitaryTax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#RealtyTax}
     */
    @FXML
    private Text RealtyTax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#LandTax}
     */
    @FXML
    private Text LandTax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#ExciseTax}
     */
    @FXML
    private Text ExciseTax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#TouristTax}
     */
    @FXML
    private Text TouristicTax;

    /**
     * This Text show the sum, which must be paid for {@link JuridicalPerson#StateDuty}
     */
    @FXML
    private Text StateDutyTax;

    /**
     * This is a field, where user have to enter his/her/its income.
     */
    @FXML
    private TextField income;

    /**
     * This is a field, where user have to enter his/her/its prime cost of running business.
     */
    @FXML
    private TextField primeCost;

    /**
     * This is a field, where user have to enter his/her/its {@link JuridicalPerson#Emissions}.
     */
    @FXML
    private TextField Emissions;

    /**
     * This is a field, where user have to enter his/her/its {@link JuridicalPerson#Salaries}.
     */
    @FXML
    private TextField Salaries;

    /**
     * This is a field, where user have to enter his/her/its {@link JuridicalPerson#NumberOfTourists} (only if it is a touristic business).
     */
    @FXML
    private TextField NumberofTourists;

    /**
     * This button saves changes (setting additional information about the business).
     */
    @FXML
    private Button ConfirmButton;

    /**
     * You have to choose whether you business is a hotel or something like that.
     */
    @FXML
    private CheckBox Touristic;

    /**
     * This button creates State Duty, based on information in {@link #priceStateDuty}.
     */
    @FXML
    private Button AddStateDutyButton;

    /**
     * The cost of the goods, which cause state duty.
     */
    @FXML
    private TextField priceStateDuty;

    /**
     * This button returns you yo {@link Controller_LogIn}.
     */
    @FXML
    private Button LogOutButton;

    /**
     * There you have to provide the information whether you work with alcohol and cigarettes.
     */
    @FXML
    private CheckBox Excise;

    /**
     * There you have to provide the information whether you use any natural resources.
     */
    @FXML
    private CheckBox UseMinRes;

    @FXML
    private Text Hello;

    @FXML
    private Text numbtext;

    /**
     * There you have to provide the information what part of your income is from excised goods (in percents).
     */
    @FXML
    private TextField PersentageExcise;

    @FXML
    private Text excisetext;

    /**
     * There user have to write square of it's realty.
     */
    @FXML
    private TextField Square;

    /**
     * This button creates a transaction.
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
     */
    @FXML
    void initialize() throws IOException {

        String type;
        final int[] Number = new int[1];
        JuridicalPerson finalJp = null;

        BufferedReader br = null;
        br = new BufferedReader(new FileReader("/Users/olesia/Desktop/FIIT STU/2 semester/OOP/Project 1/oop-2019-uto-16-c-sulaimankhail-olesiamelnychyn/E-Taxpayer_FX/src/sample/User/number.txt"));
        if (br != null) {
            type = br.readLine();
            final String[] number = {br.readLine()};
            System.out.println(number[0]);

            Number[0] = Integer.valueOf(number[0]);

            Users jp = new Users();

            if (type.equals("ent")) {
                try {
                    jp.CreateEntrepreneur2();
                } catch (ClassNotFoundException e) {
                    System.out.print("");
                }
                finalJp = jp.entr2.get(Number[0]).juridicalPerson;
            } else if (type.equals("jr")) {
                try {
                    jp.CreateJuridical();
                } catch (ClassNotFoundException e) {
                    System.out.print("");
                }
                finalJp = jp.jr.get(Number[0]);
            }

            Clear();

            SetBasicInf(type, finalJp);

            JuridicalPerson finalJp1 = finalJp;
            ConfirmButton.setOnAction(event -> {
                if (income.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})") && primeCost.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})") &&
                        Emissions.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})") && NumberofTourists.getText().matches("\\d+") &&
                        PersentageExcise.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})") && (Double.valueOf(PersentageExcise.getText())<=100) &&
                        Square.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})")){
                    finalJp1.SetAdditionalInf(Double.valueOf(income.getText()), Double.valueOf(primeCost.getText()), Excise.isSelected(), UseMinRes.isSelected(), Double.valueOf(Emissions.getText()), Double.valueOf(Salaries.getText()), Double.valueOf(Square.getText()), Touristic.isSelected(), Integer.valueOf(NumberofTourists.getText()), Double.valueOf(PersentageExcise.getText()));
                    for (Tax tax : finalJp1.Taxes
                    ) {
                        if (tax != null)
                            tax.NewCalculate();
                    }
                    update(finalJp1);

                    if (type.equals("jr")) {
                        Number[0] = ResaveinBase(jp, Number[0], finalJp1);
                    } else {
                        NaturalPersonEntrepreneur2 naturalPersonEntrepreneur2 = new NaturalPersonEntrepreneur2(finalJp1);
                        Number[0] = ResaveinBase(jp, Number[0], naturalPersonEntrepreneur2);
                    }

                    number[0] = String.valueOf(Number[0]);
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Problem");
                    alert.setHeaderText(null);
                    alert.setContentText("It is written unexpected character.");

                    alert.showAndWait();
                }
            });

            Excise.setOnAction(event -> {
                if (Excise.isSelected()) {
                    PersentageExcise.setVisible(true);
                    excisetext.setVisible(true);
                } else {
                    PersentageExcise.setVisible(false);
                    excisetext.setVisible(false);
                }
            });

            Touristic.setOnAction(event -> {
                if (Touristic.isSelected()) {
                    finalJp1.touristic = true;
                    NumberofTourists.setVisible(true);
                    numbtext.setVisible(true);
                } else {
                    finalJp1.touristic = false;
                    NumberofTourists.setVisible(false);
                    numbtext.setVisible(false);
                }
            });

            AddStateDutyButton.setOnAction(event -> {
                if(priceStateDuty.getText().matches("[0-9]+|[0-9]+(\\.[0-9]{1,2})")) {
                    finalJp1.CreateStateDuty(Integer.valueOf(priceStateDuty.getText()));
                    finalJp1.StateDuty.NewCalculate();
                    if (finalJp1.StateDuty.CurrentBalance != 0) {
                        StateDutyText.setVisible(true);
                        StateDutyTax.setVisible(true);
                        StateDutyTax.setText(String.valueOf(finalJp1.StateDuty.CurrentBalance));
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Problem");
                    alert.setHeaderText(null);
                    alert.setContentText("It is written unexpected character or nothing.");

                    alert.showAndWait();
                }
            });

            Pay.setOnAction(event -> {
                int i = 0, j = 0;
                String type1 = (String) TypeofTax.getValue();
                Transaction transaction = null;
                for (Tax x : finalJp1.Taxes) {
                    if (type1.equals("Value Added Tax") && (x instanceof ValueAddedTax) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.VAT);
                        i++;
                    } else if (type1.equals("Single Social Contribution") && (x instanceof SingleSocialContribution) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.SingleSocialContribution);
                        i++;
                    } else if (type1.equals("Income Tax") && (x instanceof IncomeTaxofJuridicalPerson) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.IncomeTax);
                        i++;
                    } else if (type1.equals("Mineral Resources Tax") && (x instanceof RentForUseOfMineralResources) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.MineralResourcesTax);
                        i++;
                    } else if (type1.equals("Ecological Tax") && (x instanceof EcologicalTax) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.EcologicalTax);
                        i++;
                    } else if (type1.equals("Income of Natural Person Tax") && (x instanceof IncomeTaxofNaturalPersons) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.IncomeNatPerTax);
                        i++;
                    } else if (type1.equals("Military Tax") && (x instanceof MilitaryFee) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.MilitaryTax);
                        i++;
                    } else if (type1.equals("Realty Tax") && (x instanceof RealEstateTaxforJuridicalPerson) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.RealtyTax);
                        i++;
                    } else if (type1.equals("Land Tax") && (x instanceof LandTaxforJuridicalPerson) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.LandTax);
                        i++;
                    } else if (type1.equals("Excise Tax") && (x instanceof ExciseTax) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.ExciseTax);
                        i++;
                    } else if (type1.equals("Touristic Tax") && (x instanceof TouristTax) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.TouristTax);
                        i++;
                    } else if (type1.equals("State Duty") && (x instanceof StateDuty) && x.CurrentBalance != 0.0) {
                        transaction = new Transaction(type1, new Date(), Double.valueOf(Amount.getValue()), Comment.getText(), finalJp1.StateDuty);
                        i++;
                    } else {
                        j++;
                    }
                    if (i == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText(null);
                        alert.setContentText("The transaction have been carried out successfully! It will appear in your history after being approved by administrator.");
                        alert.showAndWait();
                        finalJp1.transactions.add(transaction);
                        if (type.equals("jr")) {
                            Number[0] = ResaveinBase(jp, Number[0], finalJp1);
                        } else {
                            NaturalPersonEntrepreneur2 naturalPersonEntrepreneur2 = new NaturalPersonEntrepreneur2(finalJp1);
                            Number[0] = ResaveinBase(jp, Number[0], naturalPersonEntrepreneur2);
                        }
                        break;
                    }
                    if (j == 11) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Problem");
                        alert.setHeaderText(null);
                        alert.setContentText("There is nothing to pay. Maybe you haven't entered additional information for this tax or you have already paid for it. This transaction cannot be finished.");
                        alert.showAndWait();
                    }
                    i = 0;
                }
            });

            Details.setOnAction(event -> {
                int i = ListTransactions.getSelectionModel().getSelectedIndex();
                Transaction tr = finalJp1.transactions.get(i);

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
     * This method sets data, which can be changed during work in userpage.
     *
     * @param finalJp1
     */
    private void update(JuridicalPerson finalJp1) {
        VATax.setText(String.valueOf(finalJp1.VAT.CurrentBalance));
        IncomeTax.setText(String.valueOf(finalJp1.IncomeTax.CurrentBalance));
        if (finalJp1.excise) {
            ExciseTax.setText(String.valueOf(finalJp1.ExciseTax.CurrentBalance));
        } else
            ExciseTax.setText(String.valueOf(0.0));
        if (finalJp1.MineralResourcesTax != null) {
            MineralResourcesTax.setText(String.valueOf(finalJp1.MineralResourcesTax.CurrentBalance));
        } else
            MineralResourcesTax.setText(String.valueOf(0.0));
        EcologicalTax.setText(String.valueOf(finalJp1.EcologicalTax.CurrentBalance));
        SingleSocialContributionTax.setText(String.valueOf(finalJp1.SingleSocialContribution.CurrentBalance));
        IncomeNatPerTax.setText(String.valueOf(finalJp1.IncomeNatPerTax.CurrentBalance));
        MilitaryTax.setText(String.valueOf(finalJp1.MilitaryTax.CurrentBalance));
        RealtyTax.setText(String.valueOf(finalJp1.RealtyTax.CurrentBalance));
        LandTax.setText(String.valueOf(finalJp1.LandTax.CurrentBalance));
        if (finalJp1.TouristTax != null) {
            TouristicTax.setText(String.valueOf(finalJp1.TouristTax.CurrentBalance));
            TouristicTax.setVisible(true);
            TouristicText.setVisible(true);
        }
    }

    /**
     * This methods sets data, which cannot be changed while working in userpage.
     *
     * @param finalJp
     */
    private void SetBasicInf(String type, JuridicalPerson finalJp) {
        NumberofTourists.setVisible(false);
        PersentageExcise.setVisible(false);
        excisetext.setVisible(false);
        numbtext.setVisible(false);
        TouristicText.setVisible(false);
        StateDutyText.setVisible(false);
        TouristicTax.setVisible(false);
        StateDutyTax.setVisible(false);

        Hello.setText("Hello, " + finalJp.Title + "!");

        if (type.equals("ent")) {
            Profile.setText("Name and Sername: " + finalJp.Title + "\n" +
                    "Year of birth: " + finalJp.BirthYear + "\n" +
                    "Location: " + finalJp.GetLocation() + "\n" +
                    "Identification code: " + finalJp.GetOSREOUCode());
        } else {
            Profile.setText("Title: " + finalJp.Title + "\n" +
                    "Year of inition: " + finalJp.BirthYear + "\n" +
                    "Location: " + finalJp.GetLocation() + "\n" +
                    "OSREOU-code: " + finalJp.GetOSREOUCode());
        }
        Square.setText(String.valueOf(finalJp.Square));

        update(finalJp);

        if (finalJp.touristic) {
            Touristic.setSelected(true);
            numbtext.setVisible(true);
            NumberofTourists.setVisible(true);
            if (finalJp.TouristTax != null) {
                if (finalJp.TouristTax.CurrentBalance != 0) {
                    TouristicTax.setVisible(true);
                    TouristicText.setVisible(true);
                }
            }
        }
        if (finalJp.excise) {
            Excise.setSelected(true);
            PersentageExcise.setVisible(true);
            excisetext.setVisible(true);
        }
        if (finalJp.StateDuty != null) {
            StateDutyText.setVisible(true);
            StateDutyTax.setVisible(true);
        }

        income.setText(String.valueOf(finalJp.Income));
        primeCost.setText(String.valueOf(finalJp.PrimeCost));
        Emissions.setText((String.valueOf(finalJp.Emissions)));
        Salaries.setText(String.valueOf(finalJp.Salaries));
        NumberofTourists.setText(String.valueOf(finalJp.NumberOfTourists));
        PersentageExcise.setText(String.valueOf(finalJp.PercentageofExcise * 100));

        for (Transaction tr : finalJp.transactions) {
            if (tr.Approved) {
                ListTransactions.getItems().add(tr.Title + "   " + tr.Date);
            }
        }
    }

    /**
     * This methods saves changed person in the "base" if it is {@link JuridicalPerson}.
     *
     * @param pp     - "base"
     * @param number - index before saving
     * @param Person - person
     * @return - index after saving
     */
    private int ResaveinBase(Users pp, int number, JuridicalPerson Person) {
        int Number = 0;
        try {
            System.out.println(number);
            Number = pp.AddorChangeJuridical(number, Person);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Number;
    }

    /**
     * This methods saves changed person in the "base" if it is {@link NaturalPersonEntrepreneur2}.
     *
     * @param pp     - "base"
     * @param number - index before saving
     * @param Person - person
     * @return - index after saving
     */
    private int ResaveinBase(Users pp, int number, NaturalPersonEntrepreneur2 Person) {
        int Number = 0;
        try {
            System.out.println(number);
            Number = pp.AddorChangeEntrepreneur2(number, Person);
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





