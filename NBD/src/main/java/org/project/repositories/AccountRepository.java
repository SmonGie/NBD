package org.project.repositories;

import org.project.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class AccountRepository {

    private final List<Account> accounts;

    public AccountRepository() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {

        accounts.add(account);
    }

    public Optional<Account> getAccount(Account account) {

        return accounts.stream()
                .filter(acc -> acc.equals(account))
                .findFirst();
    }

    public List<Account> findAccounts(Predicate<Account> predicate) {
        List<Account> result = new ArrayList<>();
        for (Account acc : accounts) {
            if (predicate.test(acc)) {
                result.add(acc);
            }
        }
        return result;
    }

    public void removeAccount(Account account) {

        accounts.remove(account);
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts); // zwraca kopię listy, aby nie modyfikować oryginalnej listy
    }
}
