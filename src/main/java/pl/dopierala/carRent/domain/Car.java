package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String model;
    private CarTypesEnum typeEnum;
    private int productionYear;
    private String color;
    private int milage;
    private CarStatusEnum statusEnum;
    private double costPerDay;
    @Transient
    private Department departmentList;
}
