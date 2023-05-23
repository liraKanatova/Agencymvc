package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;
@Entity
@Table(name = "bookings")
@Setter
@Getter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "booking_gen")
    @SequenceGenerator(name = "booking_gen",sequenceName = "booking_seq",allocationSize = 1)
    private Long id;
    @Transient
    private Long customerId;
    @Transient
    private Long houseId;



    @ManyToOne(cascade = {DETACH,MERGE,REFRESH})
    private Customer customer;
    @OneToOne(cascade = {MERGE,REFRESH,DETACH})
    private House house;

}
