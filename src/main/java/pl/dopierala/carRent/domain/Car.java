package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Car {
    private String brand;
    private String model;
    private CarTypesEnum typeEnum;
    private int productionYear;
    private String color;
    private int milage;
    private CarStatusEnum statusEnum;
    private double costPerDay;
    private List<Department> departmentList;
}
