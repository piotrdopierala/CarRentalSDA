package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Employee {
    private String firstName;
    private String lastName;
    private boolean isManager;
    private Department dep;

    @Override
    public String toString() {
        return "Employee(" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isManager=" + isManager +
                ", dep=" + dep.getAddress() +
                ')';
    }
}
