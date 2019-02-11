package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate reservationDate;
    @OneToOne
    private Client client;
    @OneToOne
    private Car car;
    private LocalDate fromDate;
    private LocalDate toDate;
    @OneToOne
    private Department startDepartment;
    @OneToOne
    private Department endDepartment;
    private double finalCost;
}
