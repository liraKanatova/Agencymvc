package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.HouseType;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "houses")
@Setter
@Getter
@NoArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "house_gen")
    @SequenceGenerator(name = "house_gen",sequenceName = "house_seq",allocationSize = 1)
    private Long id;
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    private String address;
    private int price;
    private int room;
    private String country;
    private String description;
    private Boolean isBooked;
    @Column(length = 1000)
    private String image;
    @Transient
    private Long agencyId;
    @ManyToOne(cascade = {DETACH,REFRESH,MERGE})
    private Agency agency;
    @OneToOne(mappedBy = "house",cascade = {DETACH,REFRESH,MERGE})
    private Booking booking;

    public House(HouseType houseType, String address, int price, int room, String country, String description, boolean isBooked, String image) {
        this.houseType = houseType;
        this.address = address;
        this.price = price;
        this.room = room;
        this.country = country;
        this.description = description;
        this.isBooked = isBooked;
        this.image = image;
    }
}

