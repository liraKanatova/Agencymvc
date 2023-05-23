package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;


@Entity
@Table(name = "agencies")
@Setter
@Getter
@NoArgsConstructor
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "agency_gen")
    @SequenceGenerator(name = "agency_gen",sequenceName = "agency_seq",allocationSize = 1)
    private Long id;
//    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2,max = 30,message = "Name should be between 2 and 30 characters")
    private String agencyName;
//    @NotEmpty(message = "Country should not be empty")
//    @Size(min = 2,max = 30,message = "Country should be between 2 and 30 characters")
    private String country;
//    @Pattern(regexp = "^\\+996\\d{9}$", message = "Phone number must start with +996 and contain 12 digits")
    private String phoneNumber;
//    @Column(name = "Email",unique = true)
    private String email;
//    @Column(length = 1000)
    private String image;
    @OneToMany(mappedBy = "agency",cascade = {DETACH,MERGE,REFRESH}, fetch = FetchType.EAGER)
    private List<House> houses;

    public void addHouse(House house){
        if(houses==null){
            houses=new ArrayList<>();
        }
        houses.add(house);
    }
    @ManyToMany(cascade = {DETACH,REFRESH,MERGE,REMOVE}, fetch = FetchType.EAGER)
    private List<Customer> customers = new ArrayList<>();



    public Agency(String agencyName, String country, String phoneNumber, String email, String image) {
        this.agencyName = agencyName;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.image = image;
    }
}
