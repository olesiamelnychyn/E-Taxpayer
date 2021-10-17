package sample.Taxes;

import java.io.Serializable;

/**Abstract class Tax is an interface for all taxes.
 *<br>
 * It has methods:<br>
 * {@link Tax#calculateTax()}<br>
 * {@link Tax#NewCalculate()}<br>
 * {@link Tax#NewMonth()}<br>
 * {@link Tax#Pay}<br>
 *
 * ...and fields:<br>
 * {@link Tax#Overpayment}<br>
 * {@link Tax#Underpayment}<br>
 * {@link Tax#CurrentBalance}<br>
 */
public abstract class Tax implements Serializable {
    private boolean Overpayment = false;
    private boolean Underpayment = true;
    /**
     * This field shows how much money must be payed relatively to this tax
     *
     * @see Tax
     */
    public double CurrentBalance = 0;

    /**Method calculates the amount of money which must be paid monthly
     * Each tax has his own implementation ( is calculated differently) that's why method is abstract
     * It is used at the moment of creating a tax for the first time
     * @return the amount of money which must be paid monthly
     *
     * @see Tax
     */
    public abstract double calculateTax();

    /** Method is made to change or show how much money must be payed relatively to this tax
     *
     * @see Tax
     */
    public void NewCalculate(){
        CurrentBalance=0-calculateTax();
    }

    public void Pay(double sum) {
        CurrentBalance += sum;
        CheckPayment();

    }

    public void NewMonth() {
        CurrentBalance -= calculateTax();
        CheckPayment();
    }
    private void CheckPayment() {
        if (CurrentBalance == 0) {
            Underpayment = false;
            Overpayment = false;
        } else if (CurrentBalance > 0) {
            Underpayment = false;
            Overpayment = true;
        } else if (CurrentBalance < 0) {
            Underpayment = true;
            Overpayment = false;
        }
    }
}
