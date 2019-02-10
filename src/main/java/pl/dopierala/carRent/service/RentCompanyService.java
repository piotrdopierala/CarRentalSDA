package pl.dopierala.carRent.service;

import pl.dopierala.carRent.domain.Car;
import pl.dopierala.carRent.domain.Department;
import pl.dopierala.carRent.domain.Employee;
import pl.dopierala.carRent.domain.RentCompany;

import java.util.Optional;

public interface RentCompanyService {
    RentCompany createNewCompany(String name, String website, String address, String owner,String logo);
    void addDepartmentToCompany(RentCompany company, String depAdress);
    boolean removeDepartment(RentCompany company, String address);
    void addEmployeeToDepartment(Employee emp);
    void addCarToDepartment(Car car, Department dep);

    Optional<Department> findDepartmentByAddress(RentCompany company, String departmentAddress);
}
