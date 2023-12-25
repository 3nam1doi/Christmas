/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Flight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import utils.Utils;

/**
 *
 * @author Nguyen Trung Nam
 */
public class FlightManagement extends HashMap<String, Flight> implements I_FlightManagement {

    FlightMap flightmap = new FlightMap();
    List<String> usedGates = new ArrayList<>();

    public FlightManagement() {
    }

    public FlightManagement(FlightMap map) {
        this.flightmap = map;
    }

    public void loadFlightInformation(String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 9) {
                    String flightNumber = parts[0].trim();
                    String departureCity = parts[1].trim();
                    String destinationCity = parts[2].trim();
                    String departureTime = parts[3].trim() + " " + parts[4].trim();
                    String arrivalTime = parts[5].trim() + " " + parts[6].trim();
                    String gate = parts[7].trim();
                    int availableSeats = Integer.parseInt(parts[8].trim());

                    Flight flight = new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, gate, availableSeats);
                    this.put(flightNumber, flight);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading flight information: " + e.getMessage());
        }

    }

    public void displayDesc() {
        List<Flight> flightList = new ArrayList<>(this.values());

        Collections.sort(flightList, (o1, o2) -> {
            int makeComparison = o1.getFlightNumber().compareTo(o2.getFlightNumber());

            if (makeComparison > 0) {
                return -1;
            } else {
                return 1;
            }
        });

        System.out.println("Flight Information (Descending Order by Flight ID ):");
        for (Flight flight : flightList) {
            System.out.println(flight);
        }
    }

    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        String flightNumber;
        String departureCity;
        String destinationCity;
        String departureTime;
        String arrivalTime;
        String gate;
        int availableSeats;

        while (true) {
            do {
                flightNumber = Utils.getCode("Please enter the number of flight (FXXXX): ", "Number of flight cannot be duplicated", 1, this);

                if (!Utils.checkProductID(flightNumber)) {
                    System.out.println("The number of flight must have format Fxxxx!");
                }
            } while (!Utils.checkProductID(flightNumber));

            departureCity = Utils.getString("Please enter the departure city: ", false);
            destinationCity = Utils.getString("Please enter the destination city: ", false);

            departureTime = Utils.getDate("Please enter the departure time (YYYY/MM/DD hh:mm): ");
            arrivalTime = Utils.getValidDateTime("Please enter the arrival time (YYYY/MM/DD hh:mm): ", departureTime);

            availableSeats = Utils.getInt("Enter the number of available seats: ", 0, 180);

            while (true) {
                Random random = new Random();
                int randomGateNumber = random.nextInt(15) + 1;
                gate = String.format("%02d", randomGateNumber); 

                if (!usedGates.contains(gate)) {
                    usedGates.add(gate); 
                    break; 
                }
            }

            Flight flight = new Flight(flightNumber, departureCity, destinationCity, departureTime, arrivalTime, gate, availableSeats);
            this.put(flightNumber, flight);

            flightmap.initializeSeatMap(flightNumber, availableSeats / 6);

            System.out.println("Do you want to add another? (Y/N):");
            String choice = sc.nextLine();
            if (!choice.equalsIgnoreCase("Y")) {
                break; // Exit the loop
            }
        }
    }

    @Override
    public boolean isMatch(String flightNumber, String departureLocation, String arrivalLocation, String departureDate) {
        Flight flight = this.get(flightNumber);
        return flight.getDepartureCity().equalsIgnoreCase(departureLocation)
                && flight.getDestinationCity().equalsIgnoreCase(arrivalLocation)
                && flight.getDepartureTime().equals(departureDate)
                && flight.getAvailableSeats() > 0;
    }

    @Override
    public void bookSeat(String flightNumber) {
        Flight flight = this.get(flightNumber);
        int num = flight.getAvailableSeats();
        if (flight.getAvailableSeats() > 0) {
            flight.setAvailableSeats(num - 1);
        }
    }

}
