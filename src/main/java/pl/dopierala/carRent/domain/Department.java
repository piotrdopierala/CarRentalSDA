package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="company_id")
    private RentCompany company;
    private String address;
    @OneToMany(targetEntity = Employee.class, mappedBy = "department")
    private List<Employee> employeeList;
    @OneToMany(targetEntity = Car.class, mappedBy="department")
    private List<Car> carList;

    public Department(String address) {
        this.address = address;
        this.employeeList = new ArrayList<>();
        this.carList = new ArrayList<>();
    }

    public void addEmployee(Employee emp){
        this.employeeList.add(emp);
    }
}
