package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Client {
    private String firstName;
    private String lastName;
    private String email;
    private String address;

}
