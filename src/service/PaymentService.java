package service;

import java.util.ArrayList;
import java.util.List;

import exception.InvalidLoanException;
import model.Loan;
import model.LoanStatus;
import model.Payment;

public class PaymentService {

    private LoanService loanService;

    private List<Payment> payments =
            new ArrayList<>();

    private int paymentCounter = 5001;

    public PaymentService(LoanService loanService) {
        this.loanService = loanService;
    }

    public void makePayment(
            int loanId,
            double amount)
            throws InvalidLoanException {

        if (amount <= 0) {

            throw new InvalidLoanException(
                    "Payment must be greater than zero.");
        }

        Loan loan =
                loanService.getLoan(loanId);

        if (loan.getStatus() == LoanStatus.CLOSED) {

            throw new InvalidLoanException(
                    "This loan is already closed.");
        }

        double remaining =
                loan.getRemainingAmount();

        if (amount > remaining) {

            throw new InvalidLoanException(
                    "Payment cannot exceed remaining amount of ₹"
                    + remaining);
        }

        loan.makePayment(amount);

        Payment payment =
                new Payment(
                        paymentCounter++,
                        loanId,
                        amount);

        payments.add(payment);

        System.out.println(
                "Payment successful.");

        System.out.println(
                "Amount Paid: ₹" + amount);

        System.out.println(
                "Remaining Amount: ₹"
                + loan.getRemainingAmount());

        System.out.println(
                "Loan Status: "
                + loan.getStatus());
    }

    public void displayPayments() {

        if (payments.isEmpty()) {

            System.out.println(
                    "No payments found.");

            return;
        }

        for (Payment payment : payments) {

            System.out.println(payment);
        }
    }
}
