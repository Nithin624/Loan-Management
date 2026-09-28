package model;

import java.time.LocalDate;

public class Payment {

    private int paymentId;
    private int loanId;
    private double amount;
    private LocalDate paymentDate;

    public Payment(int paymentId, int loanId, double amount) {

        this.paymentId = paymentId;
        this.loanId = loanId;
        this.amount = amount;
        this.paymentDate = LocalDate.now();
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getLoanId() {
        return loanId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {

        return "Payment ID: " + paymentId +
                ", Loan ID: " + loanId +
                ", Amount: ₹" + amount +
                ", Date: " + paymentDate;
    }
}
