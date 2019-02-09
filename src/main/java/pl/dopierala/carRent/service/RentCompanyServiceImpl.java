package pl.dopierala.carRent.service;

import pl.dopierala.carRent.domain.Department;
import pl.dopierala.carRent.domain.Employee;
import pl.dopierala.carRent.domain.RentCompany;

public class RentCompanyServiceImpl implements RentCompanyService{

    @Override
    public RentCompany createNewCompany(String name, String website, String address, String owner, String logo) {
        RentCompany rentCompany =
                new RentCompany(name, website, address, owner, logo);
        return rentCompany;
    }

    @Override
    public void addDepartmentToCompany(RentCompany company, String depAdress) {
        company.addDepartment(new Department(depAdress));
    }

    @Override
    public boolean removeDepartment(RentCompany company, String addressToDelete) {
        return company.removeDepartment(addressToDelete);
    }

    @Override
    public void addEmployeeToDepartment(Employee emp) {
        emp.getDep().addEmployee(emp);
    }
}
