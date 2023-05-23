package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.entity.Customer;
import peaksoft.repository.CustomerRepository;
import peaksoft.service.CustomerService;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.saveCustomer(customer);

    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public List<Customer> getAllCustomers(Long id) {
        return customerRepository.getAllCustomers(id);
    }

    @Override
    public void updateCustomer(Long id, Customer newCustomer) {
        customerRepository.updateCustomer(id, newCustomer);

    }

    @Override
    public void deleteCustomerById(Long id) {
    customerRepository.deleteCustomerById(id);
    }

    @Override
    public void assignCustomerToAgency(Long customerId, List<Long> agencyIdes) {
        customerRepository.assignCustomerToAgency(customerId,agencyIdes);
    }


}
