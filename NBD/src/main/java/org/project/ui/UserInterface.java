package org.project.ui;
import org.project.model.Account;
import org.project.model.clients.Address;
import org.project.model.clients.Client;
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

            switch (wybor) {
                case "1":
                    this.loginScreen();
                    break;
                case "2":
                    this.createAccount();
                    break;
                case "q":
                case "Q":
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

            // Walidacja roku urodzenia
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
                    scanner.next(); // Odrzuca nieprawidłowe dane
                }
            }

            int age = LocalDate.now().getYear() - birthYear;

            // Walidacja numeru PESEL
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

            // Walidacja numeru telefonu
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

            // Dane adresowe
            System.out.print("Podaj miasto: ");
            city = scanner.next();

            System.out.print("Podaj ulicę: ");
            street = scanner.next();

            System.out.print("Podaj numer domu: ");
            number = scanner.next();

            // Wyświetlanie wprowadzonych danych
            System.out.println("Oto dane, które zostały wprowadzone:");
            System.out.println("Imię i Nazwisko: " + firstName + " " + lastName);
            System.out.println("Rok urodzenia: " + birthYear);
            System.out.println("Numer PESEL: " + ID);
            System.out.println("Numer telefonu: " + phoneN);
            System.out.println("Adres: " + city + ", " + street + " " + number);

            // Potwierdzenie danych
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

        // Utworzenie obiektu Account i Client
        Address address = new Address(city, street, number);
        Client client = new Client(firstName, lastName, ID, birthYear,phoneN, address);
        Account account = new Account(client);
        client.setAccount(account);

        System.out.println("Gratulujemy założenia konta!");
        System.out.println("Numer Twojego konta: " + account.getAccountNumber());

        // Ustawianie hasła
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

    // Kontynuacja innych metod
    public void loginScreen() {
        // Analogicznie przerobienie metody loginScreen na Java
    }

    // Metoda accountScreen (podobna konwersja)
    public void accountScreen(Account signedUser) {
        // Analogiczna konwersja
    }

    // Metoda transactionScreen (konwersja)
    public void transactionScreen(Account signedUser) {
        // Analogiczna konwersja
    }

    // Metoda loanScreen (konwersja)
    public void loanScreen(Account signedUser) {
        // Analogiczna konwersja
    }

}

