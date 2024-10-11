package org.project.ui;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManager;
import org.project.repositories.AccountRepository;
import org.project.repositories.ClientRepository;
import org.project.repositories.LoanRepository;
import org.project.model.clients.Client;
import org.project.model.clients.Address;


public class Main {
    public static void main(String[] args) {
        Address address = new Address("lodz","wloczanska","143");
        AccountRepository aR = new AccountRepository();
        ClientRepository cR = new ClientRepository();
        LoanRepository lR = new LoanRepository();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Client client = new Client("Jan", "Kowalski", 25, "123456789", address);
        // Rozpoczęcie transakcji
        em.getTransaction().begin();

        // Utwórz obiekt Client
        em.persist(client);

        // Zakończ transakcję
        em.getTransaction().commit();

        // Zamknij menedżera encji
        em.close();
        emf.close();

        //UserInterface ui = new UserInterface(aR, lR, cR);
        //ui.start();

    }
}