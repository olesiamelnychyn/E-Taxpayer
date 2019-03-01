package com.company.Taxes;

//interface for all taxes;
public abstract class Tax {
    boolean Overpayment = false;
    boolean Underpayment = true;
    double CurrentBalance = 0;

    public abstract double calculateTax();

    public void Pay(double sum) {
        CurrentBalance += sum;
        CheckPayment();

    }

    public void NewMounth() {
        CurrentBalance -= calculateTax();
        CheckPayment();
    }

    public void CheckPayment() {
        if (CurrentBalance == 0) {
            Underpayment = false;
            Overpayment = false;
        } else if (CurrentBalance > 0) {
            Underpayment = false;
            Overpayment = true;
        } else if (CurrentBalance > 0) {
            Underpayment = true;
            Overpayment = false;
        }
    }
}
