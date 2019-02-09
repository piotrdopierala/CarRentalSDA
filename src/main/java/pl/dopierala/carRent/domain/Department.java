package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Department {
    private String address;
    private List<Employee> employeeList;
    private List<Car> carList;
}
