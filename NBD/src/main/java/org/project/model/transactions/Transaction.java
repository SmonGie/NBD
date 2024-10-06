package org.project.model.transactions;

import org.project.model.Account;

import java.time.LocalDate;
import java.util.Random;

public class Transaction {

    public Transaction(float amount, Account recipient, Account sender) {

        this.amount = amount;
        this.recipient = recipient;
        this.sender = sender;
        date = LocalDate.now();
        ID = numberGenerator();

    }


    private float amount;
    private Account recipient, sender;
    private String ID;
    private LocalDate date;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    private String getInfo() {


        return "\n Odbiorca: " + recipient.getAccountNumber() + "\n Nadawca: " + sender.getAccountNumber()
                + "\n Kwota trasakcji: " +
                amount + "\n Data transakcji: "
                + date + "\n ID transakcji: " + ID;

    }


    void AddTransaction(Transaction t1) {



    }

    private String numberGenerator() {

        Random random = new Random();
        StringBuilder accountNumberBuild = new StringBuilder();


        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            accountNumberBuild.append(digit);
        }

        return accountNumberBuild.toString();
    }
}

