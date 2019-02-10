package pl.dopierala.carRent.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class RentCompany {
    private String name;
    private String website;
    private String address;
    private String owner;
    private String logo;
    private List<Department> departmentList;


    public RentCompany(String name, String website, String address, String owner, String logo) {
        this.name = name;
        this.website = website;
        this.address = address;
        this.owner = owner;
        this.logo = logo;
        this.departmentList = new ArrayList<>();
    }

    public void addDepartment(Department dep){
        this.departmentList.add(dep);
    }

    public boolean removeDepartment(String addressToDelete) {
        return this.departmentList.removeIf(c->c.getAddress().equalsIgnoreCase(addressToDelete));
    }
}
