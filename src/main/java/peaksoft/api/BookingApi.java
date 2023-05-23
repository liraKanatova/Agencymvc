package peaksoft.api;

import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Agency;
import peaksoft.entity.Booking;
import peaksoft.entity.Customer;
import peaksoft.entity.House;
import peaksoft.service.BookingService;
import peaksoft.service.CustomerService;
import peaksoft.service.HouseService;

import java.util.List;

@Controller
@RequestMapping("/{id}/bookings")
@RequiredArgsConstructor
public class BookingApi {
    private final BookingService bookingService;
    private final CustomerService customerService;
    private final HouseService houseService;


    @GetMapping
    String getAllBookings(Model model,
                          @PathVariable Long id) {
        model.addAttribute("houseId", id);
        model.addAttribute("houses", houseService.getAllHouses(id));
        model.addAttribute("customers", customerService.getAllCustomers(id));
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "booking/mainPage";
    }

    @GetMapping("/new")
    String create(Model model, @PathVariable Long id) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("houseId", id);
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("houses", houseService.getAllHouses());
        return "booking/newBooking";
    }

    @PostMapping("/save")
    String save(@ModelAttribute("booking") Booking booking, Model model) {
        try {
            bookingService.saveBooking(booking);
            return "redirect:/{id}/bookings";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "booking/newBooking";
        }
    }

    @DeleteMapping("/{bookingId}/delete")
    public String delete(@PathVariable("bookingId") Long bookingId,
                         @PathVariable Long id) {
        bookingService.deleteBooking(bookingId);
        return "redirect:/" + id + "/bookings";
    }

    @GetMapping("/{bookId}/edit")
    public String edit(@PathVariable("bookId") Long bookId, @PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(bookId);
        model.addAttribute("booking", booking);
        model.addAttribute("allCustomer", customerService.getAllCustomers());
        model.addAttribute("allHouse", houseService.getAllHouses());
        model.addAttribute("houseId", id);
        return "booking/edit";
    }


    @PutMapping("/{bookId}/update")
    public String update(@PathVariable("bookId") Long id, @ModelAttribute("booking") Booking booking) {
        bookingService.updateBooking(id, booking);
        return "redirect:/{id}/bookings";
    }


}
