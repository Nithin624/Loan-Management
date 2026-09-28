package service;

import exception.InvalidLoanException;
import model.Customer;
import model.Loan;
import model.PersonalLoan;
import repository.LoanRepository;
import util.PenaltyCalculator;

public class LoanService {

    private LoanRepository loanRepository;

    private CustomerService customerService;

    private EligibilityService eligibilityService;

    private int loanCounter = 1001;

    public LoanService(
            LoanRepository loanRepository,
            CustomerService customerService,
            EligibilityService eligibilityService) {

        this.loanRepository = loanRepository;
        this.customerService = customerService;
        this.eligibilityService = eligibilityService;
    }

    public Loan applyLoan(
            int customerId,
            double amount,
            double interestRate,
            int durationMonths)
            throws Exception {

        // 1. Validate amount

        if (amount <= 0) {

            throw new InvalidLoanException(
                    "Loan amount must be greater than zero.");
        }

        // 2. Validate interest

        if (interestRate <= 0) {

            throw new InvalidLoanException(
                    "Interest rate must be greater than zero.");
        }

        // 3. Validate duration

        if (durationMonths <= 0) {

            throw new InvalidLoanException(
                    "Duration must be greater than zero.");
        }

        // 4. Find customer

        Customer customer =
                customerService.getCustomer(customerId);

        // 5. Check asset eligibility

        eligibilityService.checkEligibility(
                customer,
                amount);

        // 6. Check existing active loan

        boolean hasActiveLoan =
                loanRepository.hasActiveLoan(customerId);

        double penalty = 0;

        if (hasActiveLoan) {

            penalty =
                    PenaltyCalculator.calculatePenalty(amount);

            System.out.println(
                    "Existing active loan found.");

            System.out.println(
                    "Penalty applied: ₹" + penalty);
        }

        // 7. Create loan

        Loan loan =
                new PersonalLoan(
                        loanCounter++,
                        customerId,
                        amount,
                        interestRate,
                        durationMonths,
                        penalty);

        // 8. Store loan

        loanRepository.save(loan);

        System.out.println(
                "Loan approved successfully.");

        return loan;
    }

    public Loan getLoan(int loanId)
            throws InvalidLoanException {

        Loan loan =
                loanRepository.findById(loanId);

        if (loan == null) {

            throw new InvalidLoanException(
                    "Loan with ID "
                    + loanId
                    + " not found.");
        }

        return loan;
    }

    public void displayCustomerLoans(int customerId)
            throws Exception {

        customerService.getCustomer(customerId);

        java.util.List<Loan> loans =
                loanRepository.findByCustomerId(customerId);

        if (loans.isEmpty()) {

            System.out.println(
                    "This customer has no loans.");

            return;
        }

        for (Loan loan : loans) {

            System.out.println(loan);

            System.out.println(
                    "--------------------");
        }
    }

    public void displayAllLoans() {

        if (loanRepository.findAll().isEmpty()) {

            System.out.println("No loans found.");

            return;
        }

        for (Loan loan :
                loanRepository.findAll()) {

            System.out.println(loan);

            System.out.println(
                    "--------------------");
        }
    }
}