package repository;

import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerRepository {

    private List<Customer> customers = new ArrayList<>();

    public void save(Customer customer) {
        customers.add(customer);
    }

    public Customer findById(int customerId) {

        for (Customer customer : customers) {

            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }

        return null;
    }

    public List<Customer> findAll() {
        return customers;
    }
}
