package org.project.model;

import org.project.model.clients.Client;
import org.project.model.transactions.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Account {


    public Account(Client owner) {
        this.owner = owner;
        balance = 0;
        openingDate = LocalDate.now();
        this.accountNumber = numberGenerator();


    }

    private String accountNumber;
    private float balance;
    private LocalDate openingDate;

    private Client owner;
    private Loan loan;
    private List<Transaction> transactionHistory;
    private String password;

    private String numberGenerator()
    {

        Random random = new Random();
        StringBuilder accountNumberBuild = new StringBuilder();


        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            accountNumberBuild.append(digit);
        }

        return accountNumberBuild.toString();
    }


    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    private String getOwner(){
        return owner.getInfo();
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
