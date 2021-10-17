package sample.Taxes;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class contains information about payment user did.
 */
public class Transaction implements Serializable {
    public String Title;
    public String Date;
    public Double Amount;
    public boolean Approved = false;
    public String Comment;
    public Tax Tax;
    public String DateofApproving;

    /**
     * This is a constructor which sets main data about the transaction while it's creating.
     *
     * @param title   - name of the tax.
     * @param date    - date and time when it was created
     * @param amount  - money, which was sent.
     * @param comment - message, written by a user
     * @param tax     - tax, which must be paid for.
     */
    public Transaction(String title, Date date, Double amount, String comment, Tax tax) {
        Title = title;
        Amount = amount;
        Format formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date = formatter.format(date);
        Tax = tax;
        Comment = comment;
    }

    /**
     * This method sets the {@link #DateofApproving}.
     *
     * @param date date and time when the transaction was approved by administrator.
     */
    public void SetDateofApprove(Date date) {
        Format formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        DateofApproving = formatter.format(date);
    }
}
