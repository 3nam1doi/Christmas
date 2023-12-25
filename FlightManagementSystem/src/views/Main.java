/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dto.Administrator;
import dto.CheckIn;
import dto.CrewAssigntment;
import dto.DataStorage;
import dto.FlightManagement;
import dto.FlightMap;
import dto.Reservation;
import dto.UserAccount;
import java.util.Scanner;

/**
 *
 * @author Nguyen Trung Nam
 */
public class Main {

    int choice;
    int schoice;

    public static void main(String[] args) {
        Main m = new Main();
        m.showMenu();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        FlightMap m = new FlightMap();
        FlightManagement f = new FlightManagement(m);
        Reservation p = new Reservation(f, m);
        CheckIn c = new CheckIn(p, f, m);
        CrewAssigntment r = new CrewAssigntment(f);
        Administrator ad = new Administrator(f, r);
        UserAccount u = new UserAccount();
        DataStorage ds = new DataStorage(m, f, p, c, r);

        u.loadFromFile("src/output/user.dat");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        do {

            Menu menu = new Menu("|--------------- Menu ----------------|");
            menu.addNewOptiont("1. Adding New Flight.");
            menu.addNewOptiont("2. Passenger Reservation.");
            menu.addNewOptiont("3. Passenger Check-In.");
            menu.addNewOptiont("4. Administrator Access.");
            menu.addNewOptiont("5. Save	to file.");
            menu.addNewOptiont("6. Print all lists from file.");
            menu.addNewOptiont("7. Print all lists passenger.");
            menu.addNewOptiont("8. Close the application.");
            menu.showMenu();
            System.out.println(" |-------------------------------------|");

//            String username = scanner.nextLine();
            choice = menu.getChoice();
            System.out.println("---------------------------------------");

            //Adding New Flight
            if (choice == 1) {
                System.out.println("#### Adding New Flight ####");
                f.add();
            }

            //Passenger Reservation
            if (choice == 2) {
                System.out.println("#### Passenger Reservation ####");
                p.reserve();
            }

            //Passenger Check-In
            if (choice == 3) {
                System.out.println("#### Passenger Check-In ####");
                c.perform();
            }
            if (choice == 4) {
                if (u.check(username)) {
                    do {
                        Menu smenu = new Menu("#### Manage products ####");
                        smenu.addNewOptiont("       1. Add a crew for flight");
                        smenu.addNewOptiont("       2. Update crew information for flight.");
                        smenu.addNewOptiont("       3. Show crews of flight.");
                        smenu.addNewOptiont("       4. Add account");
                        smenu.addNewOptiont("       5. Back to main menu");
                        smenu.showMenu();

                        schoice = smenu.getChoice();

                        if (schoice == 1) {
                            ad.addCrew();
                        }
                        if (schoice == 2) {
                            ad.updateCrew();
                        }
                        if (schoice == 3) {
                            ad.show();
                        }
                        if (schoice == 4) {
                            u.add();
                        }

                    } while (schoice != 5);
                } else {
                    System.out.println("Non-administrator users do not allow access to privileged features..");
                }
            }
            
            //Save To File
            if (choice == 5) {
                System.out.println("Save to file successfully.");
                ds.saveFlightInformation(f, "src/output/product.dat");
                ds.savePassengerReservations(c, "src/output/product.dat");
                ds.saveCrewAssignments(r, "src/output/product.dat");
            }
            
            //Save To File
            if (choice == 6) {
                System.out.println("#### Print all lists from file ####");
                f.loadFlightInformation("src/output/flight.dat");
                f.displayDesc();
            }
            
            if (choice == 7) {
                System.out.println("#### Print all lists of passenger ####");
                p.print();
            }
        } while (choice != 8);

    }

//        u.add();
//        f.add();
//        r.add();
//        r.update();
//        System.out.println(m.get("F0005"));
//        System.out.println(f);
//        p.reserve();
//        System.out.println(p);
//        c.perform();
//        
//        m.initializeSeatMap("F0005");
//        m.showSeatMap("F0005");
}
