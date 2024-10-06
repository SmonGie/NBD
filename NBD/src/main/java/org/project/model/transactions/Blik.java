package org.project.model.transactions;

import org.project.model.Account;

public class Blik  extends  Transaction{
    public Blik(float amount, Account recipient, Account sender) {
        super(amount, recipient, sender);
    }
}
