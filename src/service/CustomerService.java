package service;

import exception.CustomerNotFoundException;
import model.Customer;
import repository.CustomerRepository;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(Customer customer) {

        customerRepository.save(customer);

        System.out.println(
                "Customer registered successfully.");
    }

    public Customer getCustomer(int customerId)
            throws CustomerNotFoundException {

        Customer customer =
                customerRepository.findById(customerId);

        if (customer == null) {

            throw new CustomerNotFoundException(
                    "Customer with ID "
                    + customerId
                    + " not found.");
        }

        return customer;
    }

    public void displayAllCustomers() {

        if (customerRepository.findAll().isEmpty()) {

            System.out.println("No customers found.");
            return;
        }

        for (Customer customer :
                customerRepository.findAll()) {

            System.out.println(customer);
            System.out.println("--------------------");
        }
    }
}