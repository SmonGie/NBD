package org.project.model;

import jakarta.persistence.*;
import org.project.model.clients.Client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", unique = true, nullable = false)
    private Long Id;

    private String accountNumber;
    private float balance;
    private LocalDate openingDate;

    @OneToOne(mappedBy = "account")
    @JoinColumn(name = "owner_id", referencedColumnName = "Id")
    private Client owner; // Owner of the account

    @Embedded
    private Loan loan;

    //@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<Transaction> transactionHistory = new ArrayList<>();

    private String password; // Account password

    public Account(Client owner) {
        this.owner = owner;
        this.balance = 0;
        this.openingDate = LocalDate.now();
        this.accountNumber = numberGenerator(); // Generate account number
    }

    public Account() {
    }

    private String numberGenerator() {
        Random random = new Random();
        StringBuilder accountNumberBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            accountNumberBuilder.append(digit);
        }

        return accountNumberBuilder.toString();
    }

    // Getters and setters
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

    public void takeLoan(Loan loan) {
        this.loan = loan;
        addToBalance(loan.getAmount());
    }

    //public List<Transaction> getTransactionHistory() {
    // return transactionHistory;
    //}

   // public void setTransactionHistory(List<Transaction> transactionHistory) {
      //  this.transactionHistory = transactionHistory;
    //}

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void addToBalance(float amount) {
        this.balance += amount;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Client getCl() {
        return owner;
    }

    // For debugging or logging purposes
    public String getOwnerInfo() {
        return owner != null ? owner.getInfo() : "No owner assigned";
    }
}
