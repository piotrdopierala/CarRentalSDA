package pl.dopierala.carRent.service;

import pl.dopierala.carRent.domain.*;

import java.util.List;

public interface RentCompanyService {
    RentCompany createNewCompany(String name, String website, String address, String owner,String logo);
    RentCompany getCompany();

    void addDepartmentToCompany(String depAdress);
    boolean removeDepartment(String address);
    List<Department> getDepartmentsList();

    void addEmployeeToDepartment(Employee emp);

    void addClient(Client client);

    void addCarToDepartment(Car car, Department dep);
}
