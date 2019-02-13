package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.mapping.Bag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="company_id")
    private RentCompany company;
    private String address;
    @OneToMany(targetEntity = Employee.class, mappedBy = "department", fetch = FetchType.EAGER)
    private Set<Employee> employeeList;
    @OneToMany(targetEntity = Car.class, mappedBy="department", fetch = FetchType.EAGER)
    private Set<Car> carList;

    public Department(String address) {
        this.address = address;
        this.employeeList = new HashSet<>();
        this.carList = new HashSet<>();
    }

    public void addEmployee(Employee emp){
        this.employeeList.add(emp);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", company=" + company.getName() +
                ", address='" + address + '\'' +
                ", employeeList=" + employeeList +
                ", carList=" + carList +
                '}';
    }
}
