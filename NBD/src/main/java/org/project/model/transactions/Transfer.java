package org.project.model.transactions;

import org.project.model.Account;

public class Transfer extends Transaction {

    public Transfer(float amount, Account recipient, Account sender) {
        super(amount, recipient, sender);
    }
}
