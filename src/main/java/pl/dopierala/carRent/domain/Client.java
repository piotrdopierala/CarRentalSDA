package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    @ManyToOne
    @JoinColumn(name="company_id")
    private RentCompany company;

}
