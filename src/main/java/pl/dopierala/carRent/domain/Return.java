package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Return {
    private Employee employee;
    private LocalDate returnDate;
    private Reservation reservation;
    private double extraCost;
    private String additionalInfo;
}
