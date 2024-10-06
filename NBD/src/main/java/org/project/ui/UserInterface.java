package org.project.ui;
import org.project.model.Account;
import org.project.model.Loan;
import org.project.model.clients.Address;
import org.project.model.clients.Client;
import org.project.model.transactions.Transfer;
import org.project.repositories.AccountRepository;
import org.project.repositories.ClientRepository;
import org.project.repositories.LoanRepository;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class UserInterface {

    private AccountRepository aR;
    private LoanRepository lR;
    private ClientRepository cR;
    private Account signedUser;

    private String firstName, lastName, ID, phoneN, city, street, number;

    public UserInterface(AccountRepository aR, LoanRepository lR, ClientRepository cR) {
        this.aR = aR;
        this.lR = lR;
        this.cR = cR;
    }

    public void setSignedUser(Account signedUser) {
        this.signedUser = signedUser;
    }

    public int start() {
        boolean menu = false;
        Scanner scanner = new Scanner(System.in);

        while (!menu) {
            System.out.println("Witamy w naszym banku\n\n");

            System.out.println("Co chcesz zrobić?");
            System.out.println("1. Zaloguj się na konto");
            System.out.println("2. Stwórz konto");
            System.out.println("Q - Wyjście z programu\n");
            System.out.print("Twój wybór: ");

            String wybor = scanner.next();

            switch (wybor.toLowerCase()) {
                case "1":
                    this.loginScreen();
                    break;
                case "2":
                    this.createAccount();
                    break;
                case "q":
                    System.out.println("Do zobaczenia");
                    return 0;
                default:
                    System.out.println("Nie ma takiej opcji, spróbuj ponownie");
                    break;
            }
        }
        return 0;
    }

    public void createAccount() {
        System.out.println("Cieszymy się, że chcesz założyć konto w naszym banku\n");

        Scanner scanner = new Scanner(System.in);
        boolean confirm = false;

        int birthYear = 0;

        while (!confirm) {
            System.out.print("Podaj imię: ");
            firstName = scanner.next();

            System.out.print("Podaj nazwisko: ");
            lastName = scanner.next();


            boolean done1 = false;
            while (!done1) {
                System.out.print("Podaj rok urodzenia: ");
                if (scanner.hasNextInt()) {
                    birthYear = scanner.nextInt();
                    if (birthYear > 1923 && birthYear < 2020) {
                        done1 = true;
                    } else {
                        System.out.println("Podano nieprawidłowy rok. Spróbuj ponownie.");
                    }
                } else {
                    System.out.println("Nieprawidłowy format roku.");
                    scanner.next();
                }
            }

            int age = LocalDate.now().getYear() - birthYear;


            boolean done2 = false;
            while (!done2) {
                System.out.print("Podaj numer PESEL: ");
                ID = scanner.next();
                if (ID.length() == 11) {
                    done2 = true;
                } else {
                    System.out.println("Podano nieprawidłowy numer PESEL. Spróbuj ponownie.");
                }
            }


            boolean done3 = false;
            while (!done3) {
                System.out.print("Podaj numer telefonu: ");
                phoneN = scanner.next();
                if (phoneN.length() == 9) {
                    done3 = true;
                } else {
                    System.out.println("Podano nieprawidłowy numer telefonu. Spróbuj ponownie.");
                }
            }


            System.out.print("Podaj miasto: ");
            city = scanner.next();

            System.out.print("Podaj ulicę: ");
            street = scanner.next();

            System.out.print("Podaj numer domu: ");
            number = scanner.next();


            System.out.println("Oto dane, które zostały wprowadzone:");
            System.out.println("Imię i Nazwisko: " + firstName + " " + lastName);
            System.out.println("Rok urodzenia: " + birthYear);
            System.out.println("Numer PESEL: " + ID);
            System.out.println("Numer telefonu: " + phoneN);
            System.out.println("Adres: " + city + ", " + street + " " + number);


            boolean confirmInput = false;
            while (!confirmInput) {
                System.out.println("Czy wszystko się zgadza?");
                System.out.println("T - tak");
                System.out.println("N - nie");
                String conf = scanner.next();

                switch (conf.toLowerCase()) {
                    case "t":
                        confirmInput = true;
                        confirm = true;
                        break;
                    case "n":
                        System.out.println("Proszę ponownie wprowadzić dane.");
                        confirmInput = true;
                        break;
                    default:
                        System.out.println("Nie ma takiej opcji. Spróbuj ponownie.");
                        break;
                }
            }
        }


        Address address = new Address(city, street, number);
        Client client = new Client(firstName, lastName, ID, birthYear,phoneN, address);
        Account account = new Account(client);
        client.setAccount(account);

        System.out.println("Gratulujemy założenia konta!");
        System.out.println("Numer Twojego konta: " + account.getAccountNumber());


        String password;
        do {
            System.out.print("Wprowadź hasło (min 8 znaków): ");
            password = scanner.next();
            if (password.length() < 8) {
                System.out.println("Hasło musi mieć co najmniej 8 znaków.");
            }
        } while (password.length() < 8);

        account.setPassword(password);
        aR.addAccount(account);
        cR.addClient(client);

        System.out.println("Już możesz korzystać ze swojego nowo założonego konta!");

        this.start();
    }

    public void loginScreen() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Zaloguj się na swoje konto");
        boolean check = false;

        do {
            System.out.print("Numer konta: ");
            int number = scanner.nextInt();

            System.out.print("Hasło: ");
            String pass = scanner.next();

            int licz = aR.getAccounts().size();
            for (int i = 0; i < licz; i++) {
                System.out.println(aR.getAccounts().get(i).getAccountNumber());
                if (aR.getAccounts().get(i).getAccountNumber().equals(number) ) {
                    if (aR.getAccounts().get(i).getPassword().equals(pass)) {
                        signedUser = aR.getAccounts().get(i);
                        check = true;
                        break;
                    } else {
                        System.out.println("Podano nieprawidłowy numer konta lub hasło. Spróbuj ponownie.");
                        break;
                    }
                }
            }
        } while (!check);

        accountScreen(signedUser);
    }

    public void accountScreen(Account signedUser) {
        boolean menu = false;

        Scanner scanner = new Scanner(System.in);

        while (!menu) {
            System.out.println("Pulpit konta\n");
           // System.out.println(signedUser.getInfo() + "\n");

            System.out.println("Co chcesz zrobić?");
            System.out.println("1 - Transakcja na inne konto");
            System.out.println("2 - Pożyczka");
            System.out.println("3 - Historia transakcji");
            System.out.println("Q - Wyloguj się");

            String wybor = scanner.next();

            switch (wybor.toLowerCase()) {
                case "1":
                    transactionScreen(signedUser);
                    break;
                case "2":
                    loanScreen(signedUser);
                    break;
                case "3":
                    System.out.println(signedUser.getTransactionHistory());
                    accountScreen(signedUser);
                    break;
                case "q":
                    setSignedUser(null);
                    menu = true;
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja, spróbuj ponownie.");
                    break;
            }
        }

        start();
    }

    public void transactionScreen(Account signedUser) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Wybierz typ przelewu");
            System.out.println("1 - Przelew tradycyjny");
            System.out.println("2 - BLIK");
            System.out.println("Q - Wróć do pulpitu");

            String wybor = scanner.next();
            float amount = 0;

            do {
                System.out.print("Podaj kwotę przelewu: ");
                amount = scanner.nextFloat();
                if (amount < 0) {
                    System.out.println("Podana liczba jest nieprawidłowa. Spróbuj ponownie.");
                } else if (amount > signedUser.getCl().applyLimits()) {
                    System.out.println("Przekroczono limit jednorazowej transakcji");
                    System.out.println("Limit to: " + signedUser.getCl().applyLimits());
                    System.out.println("Spróbuj ponownie.");
                }
            } while (amount < 0 || amount > signedUser.getCl().applyLimits());

            switch (wybor.toLowerCase()) {
                case "1":
                   // traditionalTransfer(signedUser, amount);
                    System.out.println("Przelew chwilowo niedostepny");
                    break;
                case "2":
                    System.out.println("Blik chwilowo niedostepny");
                    //blikTransfer(signedUser, amount);
                    break;
                case "q":
                    accountScreen(signedUser);
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja, spróbuj ponownie.");
                    break;
            }
        }
    }

//    private void traditionalTransfer(Account signedUser, float amount) {
//        boolean check = false;
//
//        Scanner scanner = new Scanner(System.in);
//
//        do {
//            System.out.print("Podaj numer konta odbiorcy: ");
//            int num = scanner.nextInt();
//
//            for (Account acc : aR.getAccounts()) {
//                if (acc.getAccountNumber() == num) {
//                    confirmTransfer(acc, signedUser, amount);
//                    check = true;
//                    break;
//                } else {
//                    System.out.println("Nie ma takiego konta. Spróbuj ponownie.");
//                }
//            }
//        } while (!check);
//    }

    private void blikTransfer(Account signedUser, float amount) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj numer telefonu odbiorcy: ");
        String phone = scanner.next();

        for (Account acc : aR.getAccounts()) {
            if (acc.getCl().getPhoneNumber().equals(phone)) {
                confirmTransfer(acc, signedUser, amount);
                break;
            } else {
                System.out.println("Nie ma takiego klienta z takim numerem telefonu.");
            }
        }
    }

    private void confirmTransfer(Account recipient, Account sender, float amount) {
        boolean ch = false;

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Czy potwierdzasz przelew na konto " + recipient.getAccountNumber() + "?");
            System.out.println("T - tak");
            System.out.println("N - nie");
            String decision = scanner.next();

            switch (decision.toLowerCase()) {
                case "t":
                    Transfer tr = new Transfer(amount, recipient, sender);
                   // tr.addTransaction();
                    System.out.println("Przelew zrobiony.");
                    accountScreen(sender);
                    ch = true;
                    break;
                case "n":
                    System.out.println("Operacja porzucona.");
                    transactionScreen(sender);
                    ch = true;
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, spróbuj ponownie.");
                    break;
            }
        } while (!ch);
    }

    public void loanScreen(Account signedUser) {

        Scanner scanner = new Scanner(System.in);



        while (true) {
            System.out.println("Co chcesz zrobić?");
            if (signedUser.getLoan() == null) {
                System.out.println("1 - Weź pożyczkę");
            } else {
                System.out.println("1 - Sprawdź stan trwającej pożyczki");
            }
            System.out.println("Q - Wróć do pulpitu");

            String wybor = scanner.next();

            switch (wybor.toLowerCase()) {
                case "1":
                    if (signedUser.getLoan() == null) {
                        takeLoan(signedUser);
                    } else {
                        System.out.println(signedUser.getLoan().getInfo());
                    }
                    break;
                case "q":
                    accountScreen(signedUser);
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja, spróbuj ponownie.");
                    break;
            }
        }
    }

    private void takeLoan(Account signedUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Wprowadź kwotę pożyczki: ");
        float amount = scanner.nextFloat();
        boolean goodAmount = false;

        while (!goodAmount) {
            if (amount < 0) {
                System.out.println("Wprowadzono złą wartość. Spróbuj ponownie.");
                System.out.print("Wprowadź kwotę pożyczki: ");
                amount = scanner.nextFloat();
            } else {
                goodAmount = true;
            }
        }

        System.out.println("Wprowadzona kwota: " + amount + ". Pożyczka będzie trwała 365 dni i ma oprocentowanie 8%.");
        boolean confirm = false;

        while (!confirm) {
            System.out.println("Czy jesteś pewien, że chcesz wziąć pożyczkę?");
            System.out.println("T - tak");
            System.out.println("N - nie");
            char choice = scanner.next().charAt(0);

            switch (choice) {
                case 'T':
                case 't':
                    System.out.println("Pożyczka została zatwierdzona.");
                    Loan loan = new Loan(8, 365, amount, signedUser);
                    signedUser.takeLoan(loan);
                    lR.addLoan(loan);
                    accountScreen(signedUser);
                    confirm = true;
                    break;
                case 'N':
                case 'n':
                    System.out.println("Operacja porzucona.");
                    accountScreen(signedUser);
                    confirm = true;
                    break;
                default:
                    System.out.println("Nieprawidłowa opcja, spróbuj ponownie.");
                    break;
            }
        }
    }


}

