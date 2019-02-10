package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Rent {
    private Employee employee;
    private LocalDate rentDate;
    private Reservation reservation;
    private String additionalInfo;
}
