package peaksoft.service.serviceImpl;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Booking;
import peaksoft.entity.Customer;
import peaksoft.entity.House;
import peaksoft.repository.AgencyRepository;
import peaksoft.repository.BookingRepository;
import peaksoft.repository.CustomerRepository;
import peaksoft.repository.HouseRepository;
import peaksoft.service.BookingService;

import java.util.List;


@Service

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final HouseRepository houseRepository;
    private final AgencyRepository agencyRepository;
    private final EntityManager entityManager;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, CustomerRepository customerRepository, HouseRepository houseRepository, AgencyRepository agencyRepository, EntityManager entityManager) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.houseRepository = houseRepository;
        this.agencyRepository = agencyRepository;
        this.entityManager = entityManager;
    }


    @Override
    public void saveBooking(Booking booking) {
        House house = entityManager.find(House.class, booking.getHouseId());
        if (house.getIsBooked()){
            throw new RuntimeException("House with id %s already reserved.".formatted(house.getId()));
        }
        Customer customer = entityManager.find(Customer.class, booking.getCustomerId());
        Booking newBooking = new Booking();
        newBooking.setHouse(house);
        newBooking.setCustomer(customer);
        house.setIsBooked(true);
        bookingRepository.saveBooking(newBooking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    @Override
    public List<Booking> getAllBookings(Long id) {
        return bookingRepository.getAllBookings(id);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.getBookingById(id);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteBooking(id);
    }

    @Override
    public void updateBooking(Long id, Booking newBooking) {
        Customer customer = customerRepository.getCustomerById(newBooking.getCustomerId());
        House house = houseRepository.getHouseById(newBooking.getHouseId());
        if (house.getIsBooked()){
            throw new RuntimeException("House with id [%s] already reserved".formatted(house.getId()));
        }
        Booking booking = bookingRepository.getBookingById(id);
        booking.setHouse(house);
        booking.setCustomer(customer);
        System.out.println("in service");
        bookingRepository.updateBooking(booking);
    }
}


