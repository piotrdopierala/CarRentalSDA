package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class RentCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Employee employee;
    private LocalDate rentDate;
    @OneToOne
    private Reservation reservation;
    private String additionalInfo;
}
