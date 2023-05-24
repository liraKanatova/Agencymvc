package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.entity.Customer;
import peaksoft.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    public CustomerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveCustomer(Customer customer) {
        entityManager.persist(customer);

    }
    @Override
    public Customer getCustomerById(Long id) {
        return entityManager.find(Customer.class,id);
    }

    @Override
    public List<Customer> getAllCustomers(Long id) {
        Agency agency = entityManager.find(Agency.class, id);
        return agency.getCustomers();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return entityManager.createQuery("select c from Customer  c", Customer.class).getResultList();
    }

    @Override
    public void updateCustomer(Long id, Customer newCustomer) {

            Customer customer = entityManager.find(Customer.class, id);
//            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            LocalDate dateOfBirth = LocalDate.parse(customer.getInputDateOfBirth(), format);

            customer.setFirstName(newCustomer.getFirstName());
            customer.setLastName(newCustomer.getLastName());
            customer.setEmail(newCustomer.getEmail());
            customer.setGender(newCustomer.getGender());
            customer.setPhoneNumber(newCustomer.getPhoneNumber());
        customer.setDateOfBirth(newCustomer.getDateOfBirth());
            customer.setImage(newCustomer.getImage());
            entityManager.merge(customer);


    }

    @Override
    public void deleteCustomerById(Long id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
        for (Agency a: customer.getAgencies()) {
            a.getCustomers().remove(customer);
        }
    }






    @Override
    public void assignCustomerToAgency(Long customerId, List<Long> agencyIdes) {
        Customer customer = entityManager.find(Customer.class, customerId);
        List<Agency> agencies = new ArrayList<>();
        for (Long id : agencyIdes){
            Agency agency = entityManager.find(Agency.class, id);
            agencies.add(agency);
            agency.getCustomers().add(customer);
        }
        customer.setAgencies(agencies);
        entityManager.merge(customer);
    }
}
