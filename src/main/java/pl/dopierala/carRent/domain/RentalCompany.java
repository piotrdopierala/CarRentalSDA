package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RentalCompany {
    private String name;
    private String website;
    private String address;
    private String owner;
    private String logo;
    private List<Department> departmentList;

}
