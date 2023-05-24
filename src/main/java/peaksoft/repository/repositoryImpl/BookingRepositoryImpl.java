package peaksoft.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Booking;
import peaksoft.entity.Customer;
import peaksoft.entity.House;
import peaksoft.repository.BookingRepository;

import java.util.List;

@Repository
@Transactional
public class BookingRepositoryImpl implements BookingRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public BookingRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveBooking(Booking booking) {
        entityManager.merge(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return entityManager.createQuery("select b from Booking b ", Booking.class).getResultList();
    }

    public List<Booking> getAllBookings(Long id) {
        return entityManager.createQuery("select b from Booking b  where b.house.id =: id", Booking.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void deleteBooking(Long id) {
        entityManager.remove(entityManager.find(Booking.class, id));

    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return entityManager.find(Booking.class, bookingId);
    }

    @Transactional
    @Override
    public void updateBooking(Booking booking) {
        entityManager.merge(booking);
        System.out.println("updated");
    }


}
