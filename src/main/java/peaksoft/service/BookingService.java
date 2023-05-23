package peaksoft.service;

import peaksoft.entity.Booking;


import java.util.List;

public interface BookingService {

    void saveBooking(Booking booking);
    List<Booking>getAllBookings();
    List<Booking>getAllBookings(Long id);
    Booking getBookingById(Long id);
    void deleteBooking(Long id);
    void updateBooking(Long id,Booking newBooking);

}
