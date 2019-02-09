package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Department {
    private String address;
    private List<Employee> employeeList;
    private List<Car> carList;

    public Department(String address) {
        this.address = address;
        this.employeeList = new ArrayList<>();
        this.carList = new ArrayList<>();
    }



}
