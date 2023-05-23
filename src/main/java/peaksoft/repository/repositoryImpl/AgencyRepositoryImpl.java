package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.exception.MyException;
import peaksoft.repository.AgencyRepository;

import java.util.List;
@Repository
@Transactional
public class AgencyRepositoryImpl implements AgencyRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public AgencyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveAgency(Agency agency) {
        entityManager.persist(agency);

    }


  @Override
    public List<Agency> getAllSearch(String word) {
        if(word == null || word.isEmpty()) return entityManager.createQuery("from Agency a", Agency.class).getResultList();
        else return entityManager.createQuery("select a from Agency a where a.agencyName ilike :word or a.country ilike :word", Agency.class)
                .setParameter("word", "%" + word + "%").setParameter("word", word)
                .getResultList();
    }

    @Override
    public Agency getAgencyById(Long id) {
        return entityManager.find(Agency.class,id);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return entityManager.createQuery("select a from Agency a ", Agency.class).getResultList();
    }

    @Override
    public void updateAgency(Long id, Agency newAgency) {
        Agency agency = entityManager.find(Agency.class, id);
        agency.setAgencyName(newAgency.getAgencyName());
        agency.setCountry(newAgency.getCountry());
        agency.setPhoneNumber(newAgency.getPhoneNumber());
        agency.setEmail(newAgency.getEmail());
        agency.setImage(newAgency.getImage());
        agency.setCustomers(newAgency.getCustomers());
        agency.setHouses(newAgency.getHouses());


    }

    @Override
    public void deleteAgencyById(Long id) {
            try {
                Agency agency = entityManager.find(Agency.class, id);
                if (agency.getId().equals(id)) {
                    entityManager.remove(agency);
                } else {
                    throw new MyException("Agent of this"
                            + id + "was not found");
                }
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
    }





