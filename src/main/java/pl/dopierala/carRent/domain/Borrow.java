package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Borrow {
    private Employee employee;
    private LocalDate borrowDate;
    private Reservation reservation;
    private String comments;
}
