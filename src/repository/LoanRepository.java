package repository;

import java.util.ArrayList;
import java.util.List;

import model.Loan;
import model.LoanStatus;

public class LoanRepository {

    private List<Loan> loans = new ArrayList<>();

    public void save(Loan loan) {
        loans.add(loan);
    }

    public Loan findById(int loanId) {

        for (Loan loan : loans) {

            if (loan.getLoanId() == loanId) {
                return loan;
            }
        }

        return null;
    }

    public List<Loan> findAll() {
        return loans;
    }

    public List<Loan> findByCustomerId(int customerId) {

        List<Loan> result = new ArrayList<>();

        for (Loan loan : loans) {

            if (loan.getCustomerId() == customerId) {
                result.add(loan);
            }
        }

        return result;
    }

    public boolean hasActiveLoan(int customerId) {

        for (Loan loan : loans) {

            if (loan.getCustomerId() == customerId
                    && loan.getStatus() == LoanStatus.ACTIVE) {

                return true;
            }
        }

        return false;
    }
}