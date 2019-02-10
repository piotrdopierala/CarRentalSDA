package pl.dopierala.carRent;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.dopierala.carRent.domain.*;
import pl.dopierala.carRent.service.RentCompanyService;
import pl.dopierala.carRent.service.RentCompanyServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Main {
    private final static Scanner SCN = new Scanner(System.in);
    private final static RentCompanyService rentCompanyService = new RentCompanyServiceImpl();
    private static  SessionFactory sessionFactory;

    public static void main(String[] args) throws IOException, InterruptedException {
        setUpDB();
        mainMenuLoop();
        closeDB();
    }

    private static void setUpDB() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            throw  e;
        }
    }

    private static void closeDB(){
        sessionFactory.close();
    }

    private static void mainMenuLoop() throws IOException, InterruptedException {

        mainMenuLoop:
        while (true) {
            printMenu();
            while (!SCN.hasNextInt()) {
                System.out.println("Wprowadź cyfrę!");
                SCN.next();
            }
            int choice = SCN.nextInt();
            RentCompany company;
            switch (choice) {
                case 1:
                    System.out.println("Wprowadz nazwę:");
                    SCN.nextLine();
                    String inputName = SCN.nextLine();
                    company = rentCompanyService.createNewCompany(inputName, "www.ren.com", "ul. Glogowska 1", "Alexander Mistrz", "RRent");
                    System.out.println(company);
                    break;
                case 2:
                    deptAddDelLoop();
                    break;
                case 3:
                    company = rentCompanyService.getCompany();
                    System.out.println(company);
                    break;
                case 4:
                    createEmployee();
                    break;
                case 5:
                    addClient();
                    break;
                case 6:
                    addCar();
                    break;
                case 0:
                    break mainMenuLoop;
                default:
                    System.out.println("Choose correct option.");
            }
        }
    }

    private static void addCar() {
        System.out.println("Add Car");
        Car newCar = new Car();
        System.out.println("Enter brand:");
        SCN.nextLine();
        newCar.setBrand(SCN.nextLine());
        System.out.println("Enter model:");
        newCar.setModel(SCN.nextLine());
        System.out.println("Choose department for new employee:");
        List<Department> departmentList = rentCompanyService.getDepartmentsList();
        printDepartments();
        while (!SCN.hasNextInt()) {
            System.out.println("Wprowadź cyfrę!");
            SCN.next();
        }
        int depId = SCN.nextInt();
        if(depId<1 || depId>departmentList.size()){
            System.out.println("Given department no not found. Enter correct one.");
        }
        rentCompanyService.addCarToDepartment(newCar,departmentList.get(depId - 1));
        System.out.println(departmentList.get(depId - 1));

        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.getTransaction();
        transaction.begin();
        currentSession.persist(newCar);
        transaction.commit();

    }

    private static Client addClient() {
        System.out.println("Add client");
        Client client = new Client();
        System.out.println("Enter first name:");
        SCN.nextLine();
        client.setFirstName(SCN.nextLine());
        System.out.println("Enter last name:");
        client.setLastName(SCN.nextLine());
        rentCompanyService.addClient(client);
        return client;
    }

    private static Employee createEmployee() {
        System.out.println("Add employee");
        Employee emp = new Employee();
        System.out.println("Enter first name:");
        SCN.nextLine();
        emp.setFirstName(SCN.nextLine());
        System.out.println("Enter last name:");
        emp.setLastName(SCN.nextLine());
        System.out.println("Choose department for new employee:");
        List<Department> departmentList = rentCompanyService.getDepartmentsList();
        printDepartments();
        while (!SCN.hasNextInt()) {
            System.out.println("Wprowadź cyfrę!");
            SCN.next();
        }
        int depId = SCN.nextInt();
        if(depId<1 || depId>departmentList.size()){
            System.out.println("Given department no not found. Enter correct one.");
            return new Employee();
        }

        emp.setDep(departmentList.get(depId - 1));
        rentCompanyService.addEmployeeToDepartment(emp);
        System.out.println(departmentList.get(depId - 1));
        return emp;
    }

    private static void deptAddDelLoop() throws IOException, InterruptedException {
        RentCompany company = rentCompanyService.getCompany();
        if (company == null) {
            System.out.println("First, create company.");
            return;
        }

        deptAddDelLoop:
        while (true) {
            printDepAddDel();
            while (!SCN.hasNextInt()) {
                System.out.println("Wprowadź cyfrę!");
                SCN.next();
            }
            int choice = SCN.nextInt();
            ;
            switch (choice) {
                case 1:
                    System.out.println("NEW Department");
                    System.out.println("Enter address:");
                    SCN.nextLine();
                    String newCompanyAddress = SCN.nextLine();
                    rentCompanyService.addDepartmentToCompany(newCompanyAddress);
                    System.out.println(company);
                    break;
                case 2:
                    System.out.println("DELETE department");
                    printDepartments();
                    System.out.println("Enter address to DELETE:");
                    SCN.nextLine();
                    String delCompanyAddress = SCN.nextLine();
                    boolean delResult = rentCompanyService.removeDepartment(delCompanyAddress);
                    if (delResult) {
                        System.out.println("Department successfully deleted.");
                    } else {
                        System.out.println("No department with given address.");
                    }
                    System.out.println(company);
                    break;
                case 0:
                    break deptAddDelLoop;
                default:
                    System.out.println("Wybierz poprawną opcje");
            }
        }

    }

    private static void printDepartments() {
        System.out.println("List od departments:");
        AtomicInteger no = new AtomicInteger(1);
        rentCompanyService.getDepartmentsList().stream().map(d -> no.getAndIncrement() + " addres:" + d.getAddress()).forEach(System.out::println);
    }

    private static void printMenu() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("---- CAR RENTAL ----");
        System.out.println("1. Add new company");
        System.out.println("2. Add/Delete department");
        System.out.println("3. Display company with departments");
        System.out.println("4. Add employee");
        System.out.println("5. Add client");
        System.out.println("6. Add car");
        System.out.println("0. EXIT");
        System.out.println("Your choice:");
    }

    private static void printDepAddDel() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("---- Add/Delete department ----");
        System.out.println("1. Add department");
        System.out.println("2. Delete department");
        System.out.println("0. RETURN TO MAIN MENU");
        System.out.println("Your choice:");
    }
}
