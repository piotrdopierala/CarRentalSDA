package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Return {
    private Employee employee;
    private LocalDate returnDate;
    private Reservation reservation;
    private double extraCharge;
    private String comments;
}
