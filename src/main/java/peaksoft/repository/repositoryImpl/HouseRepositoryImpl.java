package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Agency;
import peaksoft.entity.Customer;
import peaksoft.entity.House;
import peaksoft.exception.MyException;
import peaksoft.repository.HouseRepository;

import java.util.List;
@Repository
@Transactional
public class HouseRepositoryImpl implements HouseRepository {
    @PersistenceContext
    private final EntityManager entityManager;
     @Autowired
    public HouseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveHouse(House house) {
        entityManager.persist(house);
    }

    @Override
    public House getHouseById(Long id) {
        return entityManager.find(House.class,id);
    }

    @Override
    public List<House> getAllHouses(Long id) {
           return entityManager.createQuery("select h from House  h join h.agency a where a.id = :id", House.class)
                   .setParameter("id",id)
                   .getResultList();
    }

    @Override
    public void updateHouse(Long id, House newHouse) {
        House house = entityManager.find(House.class, id);
        house.setHouseType(newHouse.getHouseType());
        house.setAddress(newHouse.getAddress());
        house.setPrice(newHouse.getPrice());
        house.setRoom(newHouse.getRoom());
        house.setCountry(newHouse.getCountry());
        house.setDescription(newHouse.getDescription());
        house.setImage(newHouse.getImage());
        house.setIsBooked(newHouse.getIsBooked());
        entityManager.merge(house);
    }

    @Override
    public void deleteHouseById(Long id) {
//        try {
//            House house = entityManager.find(House.class, id);
//            if (house.getId().equals(id)){
//                entityManager.remove(house);
//            }else {
//                throw new MyException("Customer of this " + id + " was not found!");
//            }
//        }catch (MyException e){
//            System.out.println(e.getMessage());
//        }
//        House house = entityManager.find(House.class, id);
//        house.getAgency().getHouses().remove(house);
//        house.setAgency(null);
//        entityManager.remove(house);
        House house = entityManager.find(House.class, id);
        house.setAgency(null);
        house.setBooking(null);
        entityManager.remove(house);


    }

    @Override
    public List<House> getAllHouses() {
        return entityManager.createQuery("select h from House h ", House.class).getResultList();
    }
}
