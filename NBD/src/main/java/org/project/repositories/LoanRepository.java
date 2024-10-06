package org.project.repositories;

import org.project.model.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class LoanRepository {

    private final List<Loan> loans;

    public LoanRepository() {
        this.loans = new ArrayList<>();
    }

    public void addLoan(Loan loan) {

        loans.add(loan);
    }

    public Optional<Loan> getLoan(Loan loan) {

        return loans.stream()
                .filter(l -> l.equals(loan))
                .findFirst();
    }

    public List<Loan> findLoans(Predicate<Loan> predicate) {
        List<Loan> result = new ArrayList<>();
        for (Loan loan : loans) {
            if (predicate.test(loan)) {
                result.add(loan);
            }
        }
        return result;
    }

    public void removeLoan(Loan loan) {

        loans.remove(loan);
    }

    public List<Loan> getLoans() {
        return new ArrayList<>(loans); // zwraca kopię listy
    }
}

