package pl.dopierala.carRent;

import pl.dopierala.carRent.domain.RentCompany;
import pl.dopierala.carRent.service.RentCompanyService;
import pl.dopierala.carRent.service.RentCompanyServiceImpl;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private final static Scanner SCN = new Scanner(System.in);
    private final static RentCompanyService rentCompanyService = new RentCompanyServiceImpl();
    private static RentCompany company;

    public static void main(String[] args) throws IOException, InterruptedException {

        mainMenuLoop();
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

            switch (choice) {
                case 1:
                    System.out.println("Wprowadz nazwę:");
                    SCN.nextLine();
                    String inputName = SCN.nextLine();
                    company=rentCompanyService.createNewCompany(inputName, "www.ren.com", "ul. Glogowska 1", "Alexander Mistrz", "RRent");
                    System.out.println(company);
                    break;
                case 2:
                    deptAddDelLoop();
                    break;
                case 3:
                    System.out.println(company);
                    break;
                case 0:
                    break mainMenuLoop;
                default:
                    System.out.println("Wybierz poprawna opcje");
            }
        }
    }

    private static void deptAddDelLoop() throws IOException, InterruptedException {

        if(company==null){
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

            switch (choice) {
                case 1:
                    System.out.println("NEW Department");
                    System.out.println("Enter address:");
                    SCN.nextLine();
                    String newCompanyAddress= SCN.nextLine();
                    rentCompanyService.addDepartmentToCompany(company,newCompanyAddress);
                    System.out.println(company);
                    break;
                case 2:
                    System.out.println("DELETE department");
                    System.out.println("List od departments:");
                    AtomicInteger no = new AtomicInteger(1);
                    company.getDepartmentList().stream().map(d-> no.getAndIncrement() + " addres:"+d.getAddress()).forEach(System.out::println);
                    System.out.println("Enter address to DELETE:");
                    SCN.nextLine();
                    String delCompanyAddress= SCN.nextLine();
                    boolean delResult = rentCompanyService.removeDepartment(company, delCompanyAddress);
                    if(delResult) {
                        System.out.println("Department successfully deleted.");
                    }else {
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

    private static void printMenu() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        System.out.println("---- CAR RENTAL ----");
        System.out.println("1. Add new company");
        System.out.println("2. Add/Delete department");
        System.out.println("3. Display company with departments");
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
