/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.BoardingCard;
import entities.Crew;
import entities.Flight;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nguyen Trung Nam
 */
public class DataStorage {

    FlightMap flightmap = new FlightMap();
    FlightManagement flights = new FlightManagement();
    Reservation reservations = new Reservation(flights, flightmap);
    CheckIn checkins = new CheckIn(reservations, flights, flightmap);
    CrewAssigntment crews = new CrewAssigntment(flights);

    public DataStorage(FlightMap flightmap, FlightManagement flights, Reservation reservations, CheckIn checkins, CrewAssigntment crews) {
        this.flightmap = flightmap;
        this.flights = flights;
        this.reservations = reservations;
        this.checkins = checkins;
        this.crews = crews;
    }

    public DataStorage() {
    }

    public void saveFlightInformation(HashMap<String, Flight> flights, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Flight Information");
            writer.newLine();
            for (Map.Entry<String, Flight> entry : flights.entrySet()) {
                Flight flight = entry.getValue();
                String flightInfo = String.format("%s %s %s %s %s %d",
                        entry.getKey(), 
                        flight.getDepartureCity(),
                        flight.getDestinationCity(),
                        flight.getDepartureTime(),
                        flight.getArrivalTime(),
                        flight.getAvailableSeats());
                writer.write(flightInfo);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving flight information: " + e.getMessage());
        }
    }

    public void savePassengerReservations(HashMap<String, BoardingCard> CheckIn, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Passenger Reservations");
            writer.newLine();
            for (Map.Entry<String, BoardingCard> entry : CheckIn.entrySet()) {
                BoardingCard passenger = entry.getValue();
                String reservationInfo = String.format("%s %s %s %s",
                        entry.getKey(), 
                        passenger.getName(),
                        
                        passenger.getFlightNumber(),
                        passenger.getNumberOfSeat());
                writer.write(reservationInfo);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving passenger reservations: " + e.getMessage());
        }
    }

    public void saveCrewAssignments(HashMap<String, Crew> crewAssignments, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Crew Assignments");
            writer.newLine();

            for (Map.Entry<String, Crew> entry : crewAssignments.entrySet()) {
                Crew crew = entry.getValue();
                String crewInfo = String.format("%s %s %s",
                        crew.getName(),
                        crew.getFlightNumber(),
                        crew.getRole());
                writer.write(crewInfo);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving crew assignments: " + e.getMessage());
        }
    }

}
