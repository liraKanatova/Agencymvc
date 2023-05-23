package peaksoft.service;

import peaksoft.entity.Agency;
import peaksoft.entity.Customer;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    List<Customer>getAllCustomers(Long id);
    void updateCustomer(Long id, Customer newCustomer);
    void deleteCustomerById(Long id);
    void assignCustomerToAgency(Long customerId, List<Long> agencyIdes);
}
