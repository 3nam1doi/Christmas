/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BoardingCard;
import entities.Flight;
import entities.Passenger;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Nguyen Trung Nam
 */
public class CheckIn extends HashMap<String, BoardingCard> implements I_CheckIn{

    FlightMap flightmap = new FlightMap();
    FlightManagement flights = new FlightManagement();
    Reservation reservations = new Reservation(flights, flightmap);

    public CheckIn(Reservation reservations, FlightManagement flights, FlightMap flightmap) {
        this.reservations = reservations;
        this.flights = flights;
        this.flightmap = flightmap;
    }

    @Override
    public void perform() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter reservation ID: ");
            String reservationID = scanner.nextLine();

            Passenger passenger = reservations.get(reservationID);
            if (passenger != null) {
                Flight flight = reservations.getFlightByReservationID(reservationID);
                // Thực hiện chọn chỗ ngồi cho hành khách
                String seat = flightmap.getRandomAvailableSeat(flight.getFlightNumber());
                flightmap.choose(flight.getFlightNumber(), seat);

                while (true) {

                    // In thẻ lên máy bay với thông tin hành khách và chuyến bay
                    System.out.println("Boarding Pass:");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("|%-15s |%-15s |%-20s |%-20s |%-20s |%-20s |%-10s |%-10s%n", "Passenger Name", "Flight Number", "Departure Location", "Destination Location", "Departure Time", "Arrival Time", "Gate", "Seat Number");
                    System.out.printf("|%-15s |%-15s |%-20s |%-20s |%-20s |%-20s |%-10s |%-10s%n",
                            passenger.getName(),
                            flight.getFlightNumber(), flight.getDepartureCity(), flight.getDestinationCity(),
                            flight.getDepartureTime(), flight.getArrivalTime(), flight.getGate(), seat);
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");

                    System.out.println("Do you want to change the seat? (Y/N):");
                    String choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("Y")) {
                        flightmap.remove(flight.getFlightNumber(), seat);
                        flightmap.showSeatMap(flight.getFlightNumber(), flight.getAvailableSeats());
                        while (true) {
                            System.out.println("Please choose the seat: (Example: 1A)");
                            seat = scanner.nextLine();
                            if (flightmap.check(flight.getFlightNumber(), seat)) {
                                flightmap.choose(flight.getFlightNumber(), seat);
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }

                BoardingCard b = new BoardingCard(passenger.getName(), flight.getFlightNumber(), flight.getDepartureCity(),
                        flight.getDestinationCity(), flight.getDepartureTime(), flight.getArrivalTime(), flight.getGate(), seat);
                this.put(reservationID, b);
            } else {
                System.out.println("Reservation not found.");
            }

            System.out.println("Do you want to add another? (Y/N):");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Y")) {
                break; // Exit the loop
            }
        }
    }
}
