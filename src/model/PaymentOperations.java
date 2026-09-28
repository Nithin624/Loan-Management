package model;

public interface PaymentOperations {

    void makePayment(double amount);

    double getRemainingAmount();
}