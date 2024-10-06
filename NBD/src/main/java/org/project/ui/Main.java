package org.project.ui;

import org.project.repositories.AccountRepository;
import org.project.repositories.ClientRepository;
import org.project.repositories.LoanRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AccountRepository aR = new AccountRepository();
        ClientRepository cR = new ClientRepository();
        LoanRepository lR = new LoanRepository();


        UserInterface ui = new UserInterface(aR, lR, cR);
        ui.start();

    }
}