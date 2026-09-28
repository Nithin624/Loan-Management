package model;

import java.time.LocalDate;

public abstract class Loan implements PaymentOperations {

    private int loanId;
    private int customerId;
    private double principalAmount;
    private double interestRate;
    private int durationMonths;
    private double penalty;
    private double amountPaid;
    private LoanStatus status;
    private LocalDate loanDate;

    public Loan(int loanId,
                int customerId,
                double principalAmount,
                double interestRate,
                int durationMonths,
                double penalty) {

        this.loanId = loanId;
        this.customerId = customerId;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
        this.penalty = penalty;

        this.amountPaid = 0;
        this.status = LoanStatus.ACTIVE;
        this.loanDate = LocalDate.now();
    }

    // Abstract method
    public abstract double calculateInterest();

    public double calculateTotalAmount() {

        return principalAmount
                + calculateInterest()
                + penalty;
    }

    @Override
    public void makePayment(double amount) {

        amountPaid += amount;

        if (amountPaid >= calculateTotalAmount()) {

            amountPaid = calculateTotalAmount();

            status = LoanStatus.CLOSED;
        }
    }

    @Override
    public double getRemainingAmount() {

        return calculateTotalAmount() - amountPaid;
    }

    public int getLoanId() {
        return loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public double getPenalty() {
        return penalty;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    @Override
    public String toString() {

        return "\nLoan ID: " + loanId +
                "\nCustomer ID: " + customerId +
                "\nPrincipal Amount: ₹" + principalAmount +
                "\nInterest Rate: " + interestRate + "%" +
                "\nDuration: " + durationMonths + " months" +
                "\nInterest: ₹" + calculateInterest() +
                "\nPenalty: ₹" + penalty +
                "\nTotal Amount: ₹" + calculateTotalAmount() +
                "\nAmount Paid: ₹" + amountPaid +
                "\nRemaining Amount: ₹" + getRemainingAmount() +
                "\nStatus: " + status +
                "\nLoan Date: " + loanDate;
    }
}
