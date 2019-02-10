package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Reservation {
    private LocalDate reservationDate;
    private Client client;
    private Car car;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Department startDepartment;
    private Department endDepartment;
    private double finalCost;
}
