package org.project.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Loan {
    private float amount;
    private int duration;
    private final int interestRate;

    public Loan(int interestRate, int duration, float amount) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.duration = duration;
    }
    public Loan() {
        this.interestRate = 0;
        this.duration = 0;
        this.amount = 0;
    }

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
