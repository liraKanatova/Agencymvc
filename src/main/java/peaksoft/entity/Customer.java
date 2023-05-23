package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "customers")
@Setter
@Getter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen",sequenceName = "customer_seq",allocationSize = 1)
    private Long id;
//    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2,max = 30,message = "Name should be between 2 and 30 characters")
    private String firstName;
//    @NotEmpty(message = "LastName should not be empty")
//    @Size(min = 2,max = 30,message = "LastName should be between 2 and 30 characters")
    private String lastName;
//    @NotEmpty(message = "Email should not be empty")
//    @Email(message = "email should be valid")
//    @Column(name = "email",unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
//    @Pattern(regexp = "^\\+996\\d{9}$", message = "Phone number must start with +996 and contain 12 digits")
    private String phoneNumber;
    private LocalDate dateOfBirth;
    @Column(length = 1000)
    private String image;
    @ManyToMany(mappedBy = "customers",cascade = {MERGE,DETACH,REFRESH})
    private List<Agency> agencies = new ArrayList<>();
    @OneToMany(mappedBy = "customer",cascade = {MERGE,DETACH,REFRESH})
    private List<Booking> bookings;
    public void addBooking(Booking booking){
        if (bookings== null){
            bookings = new ArrayList<>();
        }
        bookings.add(booking);
    }

    public Customer(String firstName, String lastName, String email, Gender gender, String phoneNumber, LocalDate dateOfBirth, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
    }
}
