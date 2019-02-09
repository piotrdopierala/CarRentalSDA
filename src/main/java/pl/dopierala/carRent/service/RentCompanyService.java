package pl.dopierala.carRent.service;

import pl.dopierala.carRent.domain.RentCompany;

public interface RentCompanyService {
    RentCompany createNewCompany(String name, String website, String address, String owner,String logo);
    void addDepartmentToCompany(RentCompany company, String depAdress);
    boolean removeDepartment(RentCompany company, String address);
}
