package peaksoft.repository;

import peaksoft.entity.Booking;
import peaksoft.entity.Customer;
import peaksoft.entity.House;

import java.util.List;

public interface BookingRepository {
   void saveBooking(Booking booking);
   List<Booking>getAllBookings();
   void deleteBooking(Long id);
   Booking getBookingById(Long bookingId);
   void updateBooking(Booking booking);
    List<Booking> getAllBookings(Long id);
}
