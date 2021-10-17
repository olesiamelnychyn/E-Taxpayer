package sample.Controllers;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Persons.JuridicalPerson;
import sample.Persons.NaturalPersonEntrepreneur1;
import sample.Persons.NaturalPersonEntrepreneur2;
import sample.Persons.PhysicalPerson;
import sample.Taxes.Tax;
import sample.Taxes.Transaction;
import sample.User.Users;

/**
 * This is a controller of administrator's page, where he/she can see all the users, their taxes and transaction.<br>
 * Administrator can also approve the {@link Transaction},
 * which mean that the sum "came" and the {@link Tax#CurrentBalance} of user's tax will be changed at his/her next visiting page.
 */
public class Controller_AdminPage {

    /**
     * This field represents something like base od data, which the administrator is working with.
     */
    Users users = new Users();
    /**
     * It's a button, by pressing which the user will be sent to the {@link Controller_LogIn}.
     */
    @FXML
    private Button LogOutButton;
    /**
     * This ChoiceBox controls what is shown by a {@link #ListView} of the users.<br>
     * It has 7 variants: "All users", "Only physical persons", "Only juridical persons", "Only entrepreneurs persons",
     * "Taxes of physical persons", "Taxes of juridical persons", "Taxes of entrepreneurs".
     */
    @FXML
    private ChoiceBox<?> CharacteristicsChoiceBox;
    /**
     * This button make the ListView show what is chosen in {@link #CharacteristicsChoiceBox}
     */
    @FXML
    private Button SearchButton;
    /**
     * This check box control whether the result from previous search will be shown or not.
     */
    @FXML
    private CheckBox SaveResults;
    /**
     * This ListView shows the result of the search of users.
     */
    @FXML
    private ListView<String> ListView;
    /**
     * This button is mede to rewrite the result of the search, shown in the {@link Controller_AdminPage#ListView}, into .txt.
     */
    @FXML
    private Button MakeAReportButton;
    /**
     * This ChoiceBox controls what is shown in {@link Controller_AdminPage#ListViewTransaction}.<br>
     * It has 6 variants: "Transactions of all users", "Only physical persons' transactions", "Only juridical persons' transactions",
     * "Only entrepreneurs persons' transactions", "Approved transactions", "Not approved transactions".
     */
    @FXML
    private ChoiceBox<?> TransactionChoice;
    /**
     * This button make the ListView show what is chosen in {@link #ListViewTransaction}
     */
    @FXML
    private Button SearchButton1;
    /**
     * This ListView shows the result of the search of users.
     */
    @FXML
    private ListView<String> ListViewTransaction;
    /**
     * This button opens a new window, which shows information about selected transaction in {@link #ListViewTransaction}
     * and also provides a possibility to approve it, if it is not yet.
     */
    @FXML
    private Button DetailsButton;

    /**
     * It is the main method of controller, which adds functionality to buttons.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        users.CreatePhysical();
        users.CreateJuridical();
        users.CreateEntrepreneur();
        users.CreateEntrepreneur2();

        SearchButton.setOnAction(event -> {

            System.out.println("SetOnAction");
            if (!SaveResults.isSelected())
                ListView.getItems().clear();

            if (CharacteristicsChoiceBox.getValue().equals("All users")) {
                Physical();
                Juridical();
                Entrepreneur();
            } else if (CharacteristicsChoiceBox.getValue().equals("Only physical persons")) {
                Physical();
            } else if (CharacteristicsChoiceBox.getValue().equals("Only juridical persons")) {
                Juridical();
            } else if (CharacteristicsChoiceBox.getValue().equals("Only entrepreneurs persons")) {
                Entrepreneur();
            } else if (CharacteristicsChoiceBox.getValue().equals("Taxes of physical persons")) {
                TaxesPhysical();
            } else if (CharacteristicsChoiceBox.getValue().equals("Taxes of juridical persons")) {
                TaxesJuridical();
            } else if (CharacteristicsChoiceBox.getValue().equals("Taxes of entrepreneurs")) {
                TaxesEntrepreneur();
            }
        });

        MakeAReportButton.setOnAction(event -> {
            File file = new File("report.txt");
            try {     //making a report
                PrintWriter pw = new PrintWriter(file);
                if (CharacteristicsChoiceBox.getValue().equals("Only physical persons")) {
                    pw.println("------ Physical persons -----");
                } else if (CharacteristicsChoiceBox.getValue().equals("Only juridical persons")) {
                    pw.println("------ Juridical persons -----");
                } else if (CharacteristicsChoiceBox.getValue().equals("Only juridical persons")) {
                    pw.println("------ Entrepreneurs persons -----");
                }
                for (Object str : ListView.getItems()
                ) {
                    pw.println(str);
                }
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (file.exists()) { //opening the report
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });

        SearchButton1.setOnAction(event -> {
            ListViewTransaction.getItems().clear();

            if (TransactionChoice.getValue().equals("Transactions of all users")) {
                PhysicalTr();
                JuridicalTr();
                EntrepreneurTr();
            } else if (TransactionChoice.getValue().equals("Only physical persons' transactions")) {
                PhysicalTr();
            } else if (TransactionChoice.getValue().equals("Only juridical persons' transactions")) {
                JuridicalTr();
            } else if (TransactionChoice.getValue().equals("Only entrepreneurs persons' transactions")) {
                EntrepreneurTr();
            } else if (TransactionChoice.getValue().equals("Approved transactions")) {
                ApprovedTr();
            } else if (TransactionChoice.getValue().equals("Not approved transactions")) {
                NotApprovedTr();
            }
        });

        DetailsButton.setOnAction(event -> {
                DetailsPH();
                DetailsJR();
                DetailsEnt1();
                DetailsEnt2();
        });

        LogOutButton.setOnAction(event -> Open.PressedToOpen(LogOutButton, "E-Taxpayer", "Home"));
    }

    /**
     * This method made {@link #ListView} to show all {@link PhysicalPerson}s.
     */
    private void Physical() {
        for (PhysicalPerson ph : users.ph
        ) {
            System.out.println(ph.Name);
            ListView.getItems().add(ph.GetIdentificationCode() + " --- " + ph.Name + " " + ph.Surname);
        }
    }

    /**
     * This method made {@link #ListView} to show all {@link JuridicalPerson}.
     */
    private void Juridical() {
        for (JuridicalPerson jr : users.jr
        ) {
            ListView.getItems().add(jr.GetOSREOUCode() + " --- " + jr.Title);
        }
    }

    /**
     * This method made {@link #ListView} to show all {@link NaturalPersonEntrepreneur1}s and {@link NaturalPersonEntrepreneur2}s.
     */
    private void Entrepreneur() {
        for (NaturalPersonEntrepreneur1 ent : users.entr1
        ) {
            ListView.getItems().add(ent.GetIdentificationCode() + " --- " + ent.Name + " " + ent.Surname);
        }
        for (NaturalPersonEntrepreneur2 ent : users.entr2
        ) {
            ListView.getItems().add(ent.GetIdentificationCode() + " --- " + ent.Name);
        }
    }

    /**
     * This method made {@link #ListView} to show all {@link PhysicalPerson}s with the sum of which must be paid for all the taxes.
     */
    private void TaxesPhysical() {
        double total = 0;
        for (PhysicalPerson ph : users.ph
        ) {

            double taxes = (-1) * (ph.landTax.CurrentBalance + ph.realtyTax.CurrentBalance + ph.luxuryTax.CurrentBalance);
            if (ph.stateDuty != null) {
                taxes += (-1) * ph.stateDuty.calculateTax();
            }
            total += taxes;
            if (taxes != 0) {
                ListView.getItems().add(ph.GetIdentificationCode() + " --- " + ph.Name + " " + ph.Surname + " ----- Taxes: " + taxes);
            }
        }
        if (total != 0) {
            ListView.getItems().add("");
            ListView.getItems().add("Total: " + total);
        } else {
            ListView.getItems().add("There is no payments.");
        }
    }

    /**
     * This method made {@link #ListView} to show all {@link JuridicalPerson}s with the sum of which must be paid for all the taxes.
     */
    private void TaxesJuridical() {
        double total = 0;
        for (JuridicalPerson jr : users.jr
        ) {
            double taxes = 0;
            for (Tax tax : jr.Taxes
            ) {
                if (tax != null) {
                    taxes += (-1) * tax.CurrentBalance;
                }
            }
            total += taxes;
            if (taxes != 0) {
                ListView.getItems().add(jr.GetOSREOUCode() + " --- " + jr.Title + " ----- Taxes: " + String.format("%.2f", Math.round(taxes * 100.0) / 100.0));
            }
        }
        if (total != 0) {
            ListView.getItems().add("");
            ListView.getItems().add("Total: " + String.format("%.2f", Math.round(total * 100.0) / 100.0));
        } else {
            ListView.getItems().add("There is no payments.");
        }
    }

    /**
     * This method made {@link #ListView} to show all {@link NaturalPersonEntrepreneur1}s and {@link NaturalPersonEntrepreneur2}s with the sum of which must be paid for all the taxes.
     */
    private void TaxesEntrepreneur() {
        double total = 0;
        for (NaturalPersonEntrepreneur1 ent : users.entr1
        ) {
            double taxes = (-1) * (ent.singleSocialContribution.CurrentBalance + ent.singleTax.CurrentBalance);
            total += taxes;
            if (taxes != 0) {
                ListView.getItems().add(ent.GetIdentificationCode() + " --- " + ent.Name + " " + ent.Surname + " ----- Taxes: " + String.format("%.2f", Math.round(taxes * 100.0) / 100.0));
            }
        }
        for (NaturalPersonEntrepreneur2 ent : users.entr2
        ) {
            double taxes = 0;
            for (Tax tax : ent.juridicalPerson.Taxes
            ) {
                if (tax != null) {
                    taxes += (-1) * tax.CurrentBalance;
                }
            }
            total += taxes;
            if (taxes != 0) {
                ListView.getItems().add(ent.GetIdentificationCode() + " --- " + ent.Name + " ----- Taxes: " + String.format("%.2f", Math.round(taxes * 100.0) / 100.0));
            }
        }
        if (total != 0) {
            ListView.getItems().add("");
            ListView.getItems().add("Total: " + String.format("%.2f", Math.round(total * 100.0) / 100.0));
        } else {
            ListView.getItems().add("There is no payments.");
        }
    }

    /**
     * This method made {@link #ListViewTransaction} to show all transactions of {@link PhysicalPerson}s.
     */
    private void PhysicalTr(){
        for (PhysicalPerson ph : users.ph) {
            for (Transaction tr : ph.transactions) {
                ListViewTransaction.getItems().add(ph.Name + " " + ph.Surname + " --- " + tr.Title + " " + tr.Date);
            }
        }
    }

    /**
     * This method made {@link #ListViewTransaction} to show all transactions of {@link JuridicalPerson}s.
     */
    private void JuridicalTr(){
        for (JuridicalPerson jr : users.jr) {
            for (Transaction tr : jr.transactions) {
                ListViewTransaction.getItems().add(jr.Title + " --- " + tr.Title + " " + tr.Date);
            }
        }
    }

    /**
     * This method made {@link #ListViewTransaction} to show all transactions of {@link NaturalPersonEntrepreneur1}s and {@link NaturalPersonEntrepreneur2}s.
     */
    private void EntrepreneurTr(){
        for (NaturalPersonEntrepreneur1 entrepreneur1 : users.entr1) {
            for (Transaction tr : entrepreneur1.transactions) {
                ListViewTransaction.getItems().add(entrepreneur1.Name + " " + entrepreneur1.Surname + " --- " + tr.Title + " " + tr.Date);
            }
        }
        for (NaturalPersonEntrepreneur2 entrepreneur2: users.entr2) {
            JuridicalPerson jp =entrepreneur2.juridicalPerson;
            for (Transaction tr : jp.transactions) {
                ListViewTransaction.getItems().add(entrepreneur2.Name + " --- " + tr.Title + " " + tr.Date);
            }
        }
    }

    /**
     * This method made {@link #ListViewTransaction} to show all approved transactions of all the users.
     */
    private void ApprovedTr() {
        for (PhysicalPerson ph : users.ph) {
            for (Transaction tr : ph.transactions) {
                if(tr.Approved)
                ListViewTransaction.getItems().add(ph.Name + " " + ph.Surname + " --- " + tr.Title + " " + tr.Date);

            }
        }
        for (JuridicalPerson jr : users.jr) {
            for (Transaction tr : jr.transactions) {
                if(tr.Approved)
                    ListViewTransaction.getItems().add(jr.Title + " --- " + tr.Title + " " + tr.Date);

            }
        }
        for (NaturalPersonEntrepreneur1 entrepreneur1 : users.entr1) {
            for (Transaction tr : entrepreneur1.transactions) {
                if(tr.Approved)
                ListViewTransaction.getItems().add(entrepreneur1.Name + " " + entrepreneur1.Surname + " --- " + tr.Title + " " + tr.Date);
            }
        }
        for (NaturalPersonEntrepreneur2 entrepreneur2: users.entr2) {
            JuridicalPerson jp =entrepreneur2.juridicalPerson;
            for (Transaction tr : jp.transactions) {
                if(tr.Approved)
                ListViewTransaction.getItems().add(entrepreneur2.Name + " --- " + tr.Title + " " + tr.Date);
            }
        }
    }

    /**
     * This method made {@link #ListViewTransaction} to show all unapproved transactions of all the users.
     */
    private void NotApprovedTr() {
        for (PhysicalPerson ph : users.ph) {
            for (Transaction tr : ph.transactions) {
                if(!tr.Approved)
                    ListViewTransaction.getItems().add(ph.Name + " " + ph.Surname + " --- " + tr.Title + " " + tr.Date);

            }
        }
        for (JuridicalPerson jr : users.jr) {
            for (Transaction tr : jr.transactions) {
                if(!tr.Approved)
                    ListViewTransaction.getItems().add(jr.Title + " --- " + tr.Title + " " + tr.Date);

            }
        }
        for (NaturalPersonEntrepreneur1 entrepreneur1 : users.entr1) {
            for (Transaction tr : entrepreneur1.transactions) {
                if(!tr.Approved)
                    ListViewTransaction.getItems().add(entrepreneur1.Name + " " + entrepreneur1.Surname + " --- " + tr.Title + " " + tr.Date);
            }
        }
        for (NaturalPersonEntrepreneur2 entrepreneur2: users.entr2) {
            JuridicalPerson jp =entrepreneur2.juridicalPerson;
            for (Transaction tr : jp.transactions) {
                if(!tr.Approved)
                    ListViewTransaction.getItems().add(entrepreneur2.Name + " --- " + tr.Title + " " + tr.Date);
            }
        }
    }

    /**
     * This method opens the new window with the information about the {@link PhysicalPerson}'s transaction.
     */
    private void DetailsPH(){
            for (PhysicalPerson physicalPerson: users.ph) {
                System.out.println(ListViewTransaction.getSelectionModel().getSelectedItem());
                if(ListViewTransaction.getSelectionModel().getSelectedItem().contains(physicalPerson.Name+" "+physicalPerson.Surname)){
                    for (Transaction tr: physicalPerson.transactions) {
                        if(ListViewTransaction.getSelectionModel().getSelectedItem().contains(tr.Date)){
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(Open.class.getResource("/sample/Views/TransactionAdmin.fxml"));
                            try {
                                loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Parent root = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setTitle("Transaction");
                            stage.setScene(new Scene(root));
                            Controller_Transaction_Admin controller = loader.getController();
                            controller.initialize(tr, users, "ph", users.ph.indexOf(physicalPerson));
                            stage.show();
                        }
                }
            }
        }

    }

    /**
     * This method opens the new window with the information about {@link JuridicalPerson}'s transaction.
     */
    private void DetailsJR(){
        for (JuridicalPerson juridicalPerson: users.jr) {
            System.out.println(ListViewTransaction.getSelectionModel().getSelectedItem());
            if(ListViewTransaction.getSelectionModel().getSelectedItem().contains(juridicalPerson.Title)){
                for (Transaction tr: juridicalPerson.transactions) {
                    if(ListViewTransaction.getSelectionModel().getSelectedItem().contains(tr.Date)){
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Open.class.getResource("/sample/Views/TransactionAdmin.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setTitle("Transaction");
                        stage.setScene(new Scene(root));
                        Controller_Transaction_Admin controller = loader.getController();
                        controller.initialize(tr, users, "jr", users.jr.indexOf(juridicalPerson));
                        stage.show();
                    }
                }
            }
        }

    }

    /**
     * This method opens the new window with the information about {@link NaturalPersonEntrepreneur1}'s transaction.
     */
    private void DetailsEnt1(){
        for (NaturalPersonEntrepreneur1 entrepreneur1: users.entr1) {
            System.out.println(ListViewTransaction.getSelectionModel().getSelectedItem());
            if(ListViewTransaction.getSelectionModel().getSelectedItem().contains(entrepreneur1.Name + " "+entrepreneur1.Surname)){
                for (Transaction tr: entrepreneur1.transactions) {
                    if(ListViewTransaction.getSelectionModel().getSelectedItem().contains(tr.Date)){
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Open.class.getResource("/sample/Views/TransactionAdmin.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setTitle("Transaction");
                        stage.setScene(new Scene(root));
                        Controller_Transaction_Admin controller = loader.getController();
                        controller.initialize(tr, users, "ent1", users.entr1.indexOf(entrepreneur1));
                        stage.show();
                    }
                }
            }
        }

    }

    /**
     * This method opens the new window with the information about {@link NaturalPersonEntrepreneur2}'s transactions.
     */
    private void DetailsEnt2(){

        for (NaturalPersonEntrepreneur2 entrepreneur2: users.entr2) {
            System.out.println(ListViewTransaction.getSelectionModel().getSelectedItem());
            if(ListViewTransaction.getSelectionModel().getSelectedItem().contains(entrepreneur2.Name)){
                JuridicalPerson jp =entrepreneur2.juridicalPerson;
                for (Transaction tr: jp.transactions) {
                    if(ListViewTransaction.getSelectionModel().getSelectedItem().contains(tr.Date)){
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(Open.class.getResource("/sample/Views/TransactionAdmin.fxml"));
                        try {
                            loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Parent root = loader.getRoot();
                        Stage stage = new Stage();
                        stage.setTitle("Transaction");
                        stage.setScene(new Scene(root));
                        Controller_Transaction_Admin controller = loader.getController();
                        controller.initialize(tr, users, "ent2", users.entr2.indexOf(entrepreneur2));
                        stage.show();
                    }
                }
            }
        }

    }
}
