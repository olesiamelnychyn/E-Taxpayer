package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.Taxes.Transaction;

/**
 * This is a controller of the window which can be open by a user to see information about transaction.
 */
public class Controller_Transaction {

    /**
     * This is a field, where all the information about the transaction is written.
     */
    @FXML
    private Text Title;

    /**
     * This method sets the information about transaction
     *
     * @param transaction (it's information i shown).
     */
    void initialize(Transaction transaction) {
        Title.setText("Tax: " + transaction.Title + "\n" +
                "Date: " + transaction.Date + "\n" +
                "Amount: " + transaction.Amount + " hrn\n" +
                "Comment: " + transaction.Comment + "\n\n" +
                "Date of approving by administrator: " + transaction.DateofApproving);
    }
}

