package pl.dopierala.carRent.service;

import pl.dopierala.carRent.domain.*;

import java.util.List;
import java.util.Optional;

public class RentCompanyServiceImpl_Memory implements RentCompanyService {

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
        emp.getDepartment().addEmployee(emp);
    }

    @Override
    public void addClient(Client client) {
        this.company.getClients().add(client);
    }

    @Override
    public void addCarToDepartment(Car car, Department dep) {
        dep.getCarList().add(car);
    }

    @Override
    public int initializeRepository() {
        return 0;
    }

    @Override
    public int closeRepository() {
        return 0;
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
