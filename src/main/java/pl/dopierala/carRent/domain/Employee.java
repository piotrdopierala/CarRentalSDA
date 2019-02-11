package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private boolean isManager;
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    @Override
    public String toString() {
        return "Employee(" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isManager=" + isManager +
                ", department=" + department.getAddress() +
                ')';
    }
}
