package pl.dopierala.carRent.service;

import pl.dopierala.carRent.domain.Car;
import pl.dopierala.carRent.domain.Department;
import pl.dopierala.carRent.domain.Employee;
import pl.dopierala.carRent.domain.RentCompany;

import java.util.List;
import java.util.Optional;

public interface RentCompanyService {
    RentCompany createNewCompany(String name, String website, String address, String owner,String logo);
    RentCompany getCompany();

    void addDepartmentToCompany(String depAdress);
    boolean removeDepartment(String address);
    List<Department> getDepartmentsList();

    void addEmployeeToDepartment(Employee emp);

    void addCarToDepartment(Car car, Department dep);
}
