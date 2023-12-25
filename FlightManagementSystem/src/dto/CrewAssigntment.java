/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Crew;
import entities.Flight;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import utils.Utils;

/**
 *
 * @author Nguyen Trung Nam
 */
public class CrewAssigntment extends HashMap<String, Crew> implements I_CrewAssignment {

    Scanner scanner = new Scanner(System.in);
    FlightManagement flights = new FlightManagement();
    Map<String, Crew> pilotMap = new HashMap<>();
    Map<String, Crew> flightAttendantMap = new HashMap<>();
    Map<String, Crew> groundStaffMap = new HashMap<>();

    public CrewAssigntment(FlightManagement flights) {
        this.flights = flights;
    }

    @Override
    public void add() {
        System.out.println("#### Adding crews for flight ####");
        String pilot;
        String flightAttendant;
        String groundStaff;

        while (true) {
            System.out.println("Enter number of flight: ");
            String flightNumber = scanner.nextLine();
            Flight flight = flights.get(flightNumber);

            if (flight != null) {
                
                pilot = Utils.getString("Enter name of pilot: ", false);
                flightAttendant = Utils.getString("Enter name of filght attendant: ", false);
                groundStaff = Utils.getString("Enter name of ground staff: ", false);

                // Tạo các đối tượng Crew cho phi công, tiếp viên và nhân viên mặt đất
                Crew p = new Crew(pilot, flightNumber, "pilot");
                Crew f = new Crew(flightAttendant, flightNumber, "filght attendant");
                Crew g = new Crew(groundStaff, flightNumber, "ground staff");

                this.put(pilot, p);
                this.put(flightAttendant, f);
                this.put(groundStaff, g);

                pilotMap.put(flightNumber, p);
                flightAttendantMap.put(flightNumber, f);
                groundStaffMap.put(flightNumber, g);

                show(flightNumber);
            } else {
                System.out.println("Flight not found.");
            }

            System.out.println("Do you want to add another? (Y/N):");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Y")) {
                break; // Exit the loop
            }
        }
    }

    @Override
    public void update() {
        System.out.println("#### Updating crews for flight ####");
        while (true) {
            System.out.println("Enter flight number: ");
            String flightNumber = scanner.nextLine();
            Flight flight = flights.get(flightNumber);

            if (flight != null) {
                Crew currentPilot = pilotMap.get(flightNumber);
                Crew currentFlightAttendant = flightAttendantMap.get(flightNumber);
                Crew currentGroundStaff = groundStaffMap.get(flightNumber);

                // Hiển thị thông tin hiện tại của phi hành đoàn
                show(flightNumber);

                // Yêu cầu người dùng nhập thông tin cập nhật
                String updatedPilot = Utils.getString("Enter updated name of pilot: ", false);
                String updatedFlightAttendant = Utils.getString("Enter updated name of flight attendant: ", false);
                String updatedGroundStaff = Utils.getString("Enter updated name of ground staff: ", false);

                // Cập nhật thông tin phi hành đoàn
                currentPilot.setName(updatedPilot);
                currentFlightAttendant.setName(updatedFlightAttendant);
                currentGroundStaff.setName(updatedGroundStaff);

                System.out.println("Crew information updated successfully.");

                show(flightNumber);
            } else {
                System.out.println("Flight not found.");
            }

            System.out.println("Do you want to update another? (Y/N):");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Y")) {
                break; // Exit the loop
            }
        }
    }

    @Override
    public void show(String flightNumber) {
        Crew pilot = pilotMap.get(flightNumber);
        Crew flightAttendant = flightAttendantMap.get(flightNumber);
        Crew groundStaff = groundStaffMap.get(flightNumber);

        if (pilot != null && flightAttendant != null && groundStaff != null) {
            System.out.println("#### Crews for flight " + flightNumber + " ####");

            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.printf("|%-15s |%-20s |%-20s |%-15s%n", "Flight Number", "Pilot", "Flight Attendant", "Ground Staff");
            System.out.printf("|%-15s |%-20s |%-20s |%-15s%n",
                    flightNumber, pilot.getName(), flightAttendant.getName(), groundStaff.getName());
            System.out.println("-----------------------------------------------------------------------------------------");
        } else {
            System.out.println("Crew information not found for flight " + flightNumber);
        }
    }

}
