/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Flight;
import entities.Passenger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import utils.Utils;

/**
 *
 * @author Nguyen Trung Nam
 */
public class Reservation extends HashMap<String, Passenger> implements I_PassengerManager {

    FlightManagement flights = new FlightManagement();
    FlightMap flightmap = new FlightMap();
    List<String> usedReservationIDs = new ArrayList<>();

    public HashMap<String, Passenger> getReservation() {
        return this;
    }

    public Reservation(FlightManagement flights, FlightMap flightmap) {
        this.flights = flights;
        this.flightmap = flightmap;
    }

    @Override
    public void provideInforPassenger(String SelectedPlane) {
        Scanner sc = new Scanner(System.in);

        String reservationID;
        String Name;
        String Phone;
        String Email;

        while (true) {
            // Sinh reservationID ngẫu nhiên theo định dạng "VNxxxx"
            Random random = new Random();
            int randomReservationIDNumber = random.nextInt(8) + 1; 
            reservationID = "VN" + String.format("%04d", randomReservationIDNumber); // Định dạng reservationID

            if (!usedReservationIDs.contains(reservationID)) {
                usedReservationIDs.add(reservationID); 
                break; 
            }
        }
        System.out.println("The ID of reservation: " + reservationID);
        
        Name = Utils.getString("Enter name of passenger: ", false);

        Phone = Utils.getString("Enter phone number of passenger (10 digits): ", false);
        while (!Utils.isValidPhone(Phone)) {
            System.out.println("Invalid phone number. The phone number must have at least 10 digits.");
            Phone = Utils.getString("Enter phone number again: ", false);
        }

        Email = Utils.getString("Enter passenger's email (example@example.com): ", false);
        while (!Utils.isValidEmail(Email)) {
            System.out.println("Invalid email. Email must be in the format example@example.com.");
            Email = Utils.getString("Enter passenger's email again: ", false);
        }

        Passenger p = new Passenger(Name, Phone, Email, SelectedPlane);
        this.put(reservationID, p);

    }

    @Override
    public void reserve() {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter departure location: ");
            String departureLocation = scanner.nextLine();
            System.out.print("Enter arrival location: ");
            String arrivalLocation = scanner.nextLine();
            System.out.print("Enter departure date (YYYY/MM/DD hh:mm): ");
            String departureDate = scanner.nextLine();

            Map<String, Flight> matchingFlights = new HashMap<>();
            for (Flight flight : flights.values()) {
                if (flight.isMatch(departureLocation, arrivalLocation, departureDate)) {
                    matchingFlights.put(flight.getFlightNumber(), flight);
                }
            }

            if (matchingFlights.isEmpty()) {
                System.out.println("No matching flights found.");
            } else {
                System.out.println("Matching flights:");
                System.out.println("------------------------------------------------------------------------------------------------------------------------");

                System.out.printf("|%-15s |%-20s |%-20s |%-20s |%-20s |%-10s%n", "Flight Number", "Departure Location", "Destination Location", "Departure Time", "Arrival Time", "Available Seats");

                for (Flight flight : matchingFlights.values()) {
                    System.out.printf("|%-15s |%-20s |%-20s |%-20s |%-20s |%-10s%n",
                            flight.getFlightNumber(), flight.getDepartureCity(), flight.getDestinationCity(),
                            flight.getDepartureTime(), flight.getArrivalTime(), flight.getAvailableSeats());
                }
                System.out.println("------------------------------------------------------------------------------------------------------------------------");

                while (true) {
                    System.out.print("Enter the Flight Number to reserve: ");
                    String selectedFlightNumber = scanner.nextLine();
                    Flight selectedFlight = matchingFlights.get(selectedFlightNumber);

                    if (selectedFlight != null) {
                        if (selectedFlight.getAvailableSeats() > 0) {

                            selectedFlight.bookSeat();
                            System.out.println("Seat reserved successfully.");
                            System.out.println("Please enter passenger information:");
                            provideInforPassenger(selectedFlightNumber);
                            break;
                        } else {
                            System.out.println("This flight is fully booked.");

                            System.out.print("Do you want to choose another flight? (Y/N): ");
                            String choice = scanner.nextLine();
                            if (!choice.equalsIgnoreCase("Y")) {
                                break; 
                            }
                        }
                    } else {
                        System.out.println("Invalid Flight Number.");
                    }
                }
            }

            System.out.println("Do you want to add another? (Y/N):");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Y")) {
                break; // Exit the loop
            }
        }
    }

    public Flight getFlightByReservationID(String s) {
        Passenger passenger = this.get(s);
        String flightNumber = passenger.getSelectedPlane();
        Flight flight = flights.get(flightNumber);
        return flight;
    }

    @Override
    public void print() {
        List<Passenger> passengerList = new ArrayList<>(this.values());
        
        for (Passenger passenger : passengerList) {
            System.out.println(passenger);
        }
    }

}
