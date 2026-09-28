package main;

import java.util.Scanner;

import model.Asset;
import model.Customer;
import model.Loan;

import repository.CustomerRepository;
import repository.LoanRepository;

import service.CustomerService;
import service.EligibilityService;
import service.LoanService;
import service.PaymentService;

public class LoanManagementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CustomerRepository customerRepository =
                new CustomerRepository();

        LoanRepository loanRepository =
                new LoanRepository();

        CustomerService customerService =
                new CustomerService(customerRepository);

        EligibilityService eligibilityService =
                new EligibilityService();

        LoanService loanService =
                new LoanService(
                        loanRepository,
                        customerService,
                        eligibilityService);

        PaymentService paymentService =
                new PaymentService(loanService);

        int customerCounter = 1001;

        while (true) {

            System.out.println("\n==============================");
            System.out.println("    LOAN MANAGEMENT SYSTEM");
            System.out.println("==============================");

            System.out.println("1. Register Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Check Loan Eligibility");
            System.out.println("4. Apply for Loan");
            System.out.println("5. View Customer Loans");
            System.out.println("6. Make Payment");
            System.out.println("7. View All Loans");
            System.out.println("8. View Payments");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");

            int choice;

            try {

                choice = sc.nextInt();

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid number.");

                sc.nextLine();

                continue;
            }

            try {

                switch (choice) {

                case 1:

                    sc.nextLine();

                    System.out.print(
                            "Enter customer name: ");

                    String name = sc.nextLine();

                    System.out.print(
                            "Enter phone number: ");

                    String phone = sc.nextLine();

                    System.out.print(
                            "Enter address: ");

                    String address = sc.nextLine();

                    System.out.print(
                            "Enter gold value: ");

                    double gold = sc.nextDouble();

                    System.out.print(
                            "Enter land value: ");

                    double land = sc.nextDouble();

                    System.out.print(
                            "Enter house value: ");

                    double house = sc.nextDouble();

                    System.out.print(
                            "Enter vehicle value: ");

                    double vehicle = sc.nextDouble();

                    Asset asset =
                            new Asset(
                                    gold,
                                    land,
                                    house,
                                    vehicle);

                    Customer customer =
                            new Customer(
                                    customerCounter++,
                                    name,
                                    phone,
                                    address,
                                    asset);

                    customerService
                            .registerCustomer(customer);

                    System.out.println(
                            "Customer ID: "
                            + customer.getCustomerId());

                    break;

                case 2:

                    customerService
                            .displayAllCustomers();

                    break;

                case 3:

                    System.out.print(
                            "Enter customer ID: ");

                    int customerId =
                            sc.nextInt();

                    Customer customerForEligibility =
                            customerService
                                    .getCustomer(customerId);

                    System.out.print(
                            "Enter requested loan amount: ");

                    double requestedAmount =
                            sc.nextDouble();

                    double eligibleAmount =
                            eligibilityService
                                    .calculateEligibleAmount(
                                            customerForEligibility);

                    System.out.println(
                            "Total Asset Value: ₹"
                            + customerForEligibility
                                    .getAsset()
                                    .getTotalAssetValue());

                    System.out.println(
                            "Maximum Eligible Loan: ₹"
                            + eligibleAmount);

                    if (requestedAmount
                            <= eligibleAmount) {

                        System.out.println(
                                "STATUS: ELIGIBLE");

                    } else {

                        System.out.println(
                                "STATUS: NOT ELIGIBLE");
                    }

                    break;

                case 4:

                    System.out.print(
                            "Enter customer ID: ");

                    int loanCustomerId =
                            sc.nextInt();

                    System.out.print(
                            "Enter loan amount: ");

                    double loanAmount =
                            sc.nextDouble();

                    System.out.print(
                            "Enter interest rate (%): ");

                    double interestRate =
                            sc.nextDouble();

                    System.out.print(
                            "Enter duration (months): ");

                    int duration =
                            sc.nextInt();

                    Loan loan =
                            loanService.applyLoan(
                                    loanCustomerId,
                                    loanAmount,
                                    interestRate,
                                    duration);

                    System.out.println(
                            "\n========== LOAN ==========");

                    System.out.println(loan);

                    break;

                case 5:

                    System.out.print(
                            "Enter customer ID: ");

                    int searchCustomerId =
                            sc.nextInt();

                    loanService
                            .displayCustomerLoans(
                                    searchCustomerId);

                    break;

                case 6:

                    System.out.print(
                            "Enter loan ID: ");

                    int loanId =
                            sc.nextInt();

                    System.out.print(
                            "Enter payment amount: ");

                    double paymentAmount =
                            sc.nextDouble();

                    paymentService
                            .makePayment(
                                    loanId,
                                    paymentAmount);

                    break;

                case 7:

                    loanService
                            .displayAllLoans();

                    break;

                case 8:

                    paymentService
                            .displayPayments();

                    break;

                case 9:

                    System.out.println(
                            "Thank you for using "
                            + "Loan Management System.");

                    sc.close();

                    return;

                default:

                    System.out.println(
                            "Invalid choice.");

                }

            } catch (Exception e) {

                System.out.println(
                        "ERROR: "
                        + e.getMessage());
            }
        }
    }
}
