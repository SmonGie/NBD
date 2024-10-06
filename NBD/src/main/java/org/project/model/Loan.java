package org.project.model;

public class Loan {
    public Loan(int interestRate, int duration, float amount, Account loaner) {

        this.amount = amount;
        this.interestRate = interestRate;
        this.duration = duration;
        this.loanerl = loaner;
    }


    private Account loanerl;

    private float amount;

    private int duration;

    private final int interestRate;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getInfo() {

        return " \n Kwota pozyczki: " + amount + " \n Oprocentowanie: " + interestRate
                + "% \n Dlugosc pozyczki: " + duration + " dni";

    }

}
