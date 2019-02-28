package com.company.Taxes;

public abstract class Tax {
    boolean isPaid = false;

    public abstract double calculateTax();

    public void SetPaid (){
        isPaid = true;
    }

    public void SetUnPaid (){
        isPaid = false;
    }

}
