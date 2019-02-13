package pl.dopierala.carRent.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import pl.dopierala.carRent.domain.*;

import java.util.List;
import java.util.Objects;

public class RentCompanyServiceImpl_DB implements RentCompanyService {

    private static SessionFactory sessionFactory;
    private RentCompany company;

    @Override
    public RentCompany createNewCompany(String name, String website, String address, String owner, String logo) {
        RentCompany company = new RentCompany(name, website, address, owner, logo);

        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        transaction.begin();
        currentSession.persist(company);
        transaction.commit();

        this.company = company;
        return company;
    }

    @Override
    public RentCompany getCompany() {

        if (Objects.isNull(company)) {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Integer lastCompanyId = session.createQuery("SELECT MAX(id) FROM RentCompany", Integer.class).getSingleResult();
            Query query = session.createQuery("FROM RentCompany cy LEFT JOIN FETCH cy.clients cs WHERE cy.id=:id");
            query.setParameter("id", lastCompanyId);
            company = (RentCompany) query.getSingleResult();
            transaction.commit();
        }

        return company;
    }

    @Override
    public void addDepartmentToCompany(String depAdress) {
        Department newDep = new Department(depAdress);
        this.company.addDepartment(newDep);
        updateCompany();
    }

    @Override
    public boolean removeDepartment(String address) {
        company.removeDepartment(address);
        updateCompany();
        return false;
    }

    private void updateCompany() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.update(company);
        transaction.commit();
    }

    @Override
    public List<Department> getDepartmentsList() {
        return company.getDepartmentList();
    }

    @Override
    public void addEmployeeToDepartment(Employee emp) {

    }

    @Override
    public void addClient(Client client) {

    }

    @Override
    public void addCarToDepartment(Car car, Department dep) {

        dep.getCarList().add(car);

        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        transaction.begin();
        currentSession.persist(car);
        transaction.commit();
    }

    @Override
    public int initializeRepository() {
        setUpDB();
        return 0; //success
    }

    @Override
    public int closeRepository() {
        closeDB();
        return 0;//success
    }

    private static void setUpDB() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }

    private static void closeDB() {
        sessionFactory.close();
    }
}
