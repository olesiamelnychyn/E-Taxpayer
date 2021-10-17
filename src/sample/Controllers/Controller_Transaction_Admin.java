package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import sample.Persons.JuridicalPerson;
import sample.Persons.NaturalPersonEntrepreneur1;
import sample.Persons.NaturalPersonEntrepreneur2;
import sample.Persons.PhysicalPerson;
import sample.Taxes.Transaction;
import sample.User.Users;
import java.io.IOException;
import java.util.Date;

/**
 * This is a controller of the window which can be open by administrator to see information about transaction and approve it.
 *
 */
public class Controller_Transaction_Admin {

    /**
     * This is a button by pressing which administrator approves the transaction. It is available only if the transaction is not approved.
     */
    @FXML
    private Button Approve;

    /**
     * This fields contains the information about the transaction.
     */
    @FXML
    private Text Text;

    /**
     * This method add functiolity.
     *
     * @param transaction (it's information is shown)
     * @param users       - "base" to get the owner of the transaction
     * @param type        - type of person
     * @param index       is an index of the person in any araylist in {@link Users} before changes.
     */
    @FXML
    void initialize(Transaction transaction, Users users, String type, int index) {
        PhysicalPerson ph = null;
        JuridicalPerson jr = null;
        NaturalPersonEntrepreneur1 ent1 = null;
        NaturalPersonEntrepreneur2 ent2 = null;

        switch (type) {
            case "ph":
                ph = users.ph.get(index);
                break;
            case "jr":
                jr = users.jr.get(index);
                break;
            case "ent1":
                ent1 = users.entr1.get(index);
                break;
            case "ent2":
                ent2 = users.entr2.get(index);
                break;
        }
        SetText(transaction, jr, ph, ent1, ent2, type);

        PhysicalPerson finalPh = ph;
        JuridicalPerson finalJr = jr;
        NaturalPersonEntrepreneur1 finalEnt1 = ent1;
        NaturalPersonEntrepreneur2 finalEnt2 = ent2;
        Approve.setOnAction(event -> {
            System.out.println("approve");
            transaction.Tax.Pay(transaction.Amount);
            transaction.Approved = true;
            transaction.SetDateofApprove(new Date());
            try {
                switch (type) {
                    case "ph":
                        users.AddorChangePhysical(index, finalPh);
                        break;
                    case "jr":
                        users.AddorChangeJuridical(index, finalJr);
                        break;
                    case "ent1":
                        users.AddorChangeEntrepreneur(index, finalEnt1);
                        break;
                    case "ent2":
                        users.AddorChangeEntrepreneur2(index, finalEnt2);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            SetText(transaction, finalJr, finalPh, finalEnt1, finalEnt2, type);

        });
    }

    private void SetText(Transaction transaction, JuridicalPerson jr, PhysicalPerson ph, NaturalPersonEntrepreneur1 ent1,
                         NaturalPersonEntrepreneur2 ent2, String type) {
        String text = null;
        switch (type) {
            case "ph":
                text = "Name: " + ph.Name + " " + ph.Surname + "\n" + "Identification code: " + ph.GetIdentificationCode() + "\n";
                break;
            case "jr":
                text = "Title: " + jr.Title + "\n" + "Identification code: " + jr.GetOSREOUCode() + "\n";
                break;
            case "ent1":
                text = "Name: " + ent1.Name + " " + ent1.Surname + "\n" + "Identification code: " + ent1.GetIdentificationCode() + "\n";
                break;
            case "ent2":
                text = "Name: " + ent2.Name + "\n" + "Identification code: " + ent2.GetIdentificationCode() + "\n";
                break;
        }
        text += "Tax: " + transaction.Title + "\n" +
                "Date: " + transaction.Date + "\n" +
                "Amount: " + transaction.Amount + " hrn\n" +
                "Comment: " + transaction.Comment + "\n" +
                "Condition: ";
        if (transaction.Approved) {
            text += "approved";
            Approve.setVisible(false);
        } else {
            text += "not approved";
        }
        Text.setText(text);
    }
}

