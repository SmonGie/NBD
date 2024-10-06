package org.project.repositories;

import org.project.model.clients.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ClientRepository {

    private final List<Client> clients;

    public ClientRepository() {
        this.clients = new ArrayList<>();
    }

    public void addClient(Client client) {

        clients.add(client);
    }

    public Optional<Client> getClient(Client client) {
        return clients.stream()
                .filter(c -> c.equals(client))
                .findFirst();
    }

    public List<Client> findClients(Predicate<Client> predicate) {
        List<Client> result = new ArrayList<>();
        for (Client client : clients) {
            if (predicate.test(client)) {
                result.add(client);
            }
        }
        return result;
    }

    public void removeClient(Client client) {

        clients.remove(client);
    }

    public List<Client> getClients() {
        return new ArrayList<>(clients); // zwraca kopię listy
    }


}


