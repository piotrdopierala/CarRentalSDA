package pl.dopierala.carRent.service;

import pl.dopierala.carRent.domain.Car;
import pl.dopierala.carRent.domain.Department;
import pl.dopierala.carRent.domain.Employee;
import pl.dopierala.carRent.domain.RentCompany;

import java.util.List;
import java.util.Optional;

public class RentCompanyServiceImpl implements RentCompanyService {

    private RentCompany company;

    @Override
    public RentCompany createNewCompany(String name, String website, String address, String owner, String logo) {
        RentCompany newRentCompany =
                new RentCompany(name, website, address, owner, logo);
        this.company = newRentCompany;
        return newRentCompany;
    }

    @Override
    public void addDepartmentToCompany(String depAdress) {
        this.company.addDepartment(new Department(depAdress));
    }

    @Override
    public boolean removeDepartment(String addressToDelete) {
        return this.company.removeDepartment(addressToDelete);
    }

    @Override
    public void addEmployeeToDepartment(Employee emp) {
        emp.getDep().addEmployee(emp);
    }

    @Override
    public void addCarToDepartment(Car car, Department dep) {
        dep.getCarList().add(car);
    }

    @Override
    public List<Department> getDepartmentsList() {
        return this.company.getDepartmentList();
    }

    @Override
    public RentCompany getCompany() {
        return this.company;
    }


    public Optional<Department> findDepartmentByAddress(RentCompany company, String departmentAddress) {
        Optional<Department> department = company.getDepartmentList().stream().filter(dep -> dep.getAddress().equalsIgnoreCase(departmentAddress)).findFirst();
        return department;
    }

}
