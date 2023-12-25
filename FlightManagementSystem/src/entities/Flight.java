package entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nguyen Trung Nam
 */
public class Flight {

    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private String departureTime;
    private String arrivalTime;
    private String gate;
    private int availableSeats;

    public Flight(String flightNumber, String departureCity, String destinationCity, String departureTime, String arrivalTime, String gate, int availableSeats) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.gate = gate;
        this.availableSeats = availableSeats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    @Override
    public String toString() {
        return String.format("Flight Number: %-15s | Departure City: %-20s | Destination City: %-20s | Departure Time: %-15s | Arrival Time: %-15s | Gate: %-10s | Available Seats: %d",
                flightNumber, departureCity, destinationCity, departureTime, arrivalTime, gate, availableSeats);
    }

    public boolean isMatch(String departureLocation, String arrivalLocation, String departureDate) {
        return this.departureCity.equalsIgnoreCase(departureLocation)
                && this.destinationCity.equalsIgnoreCase(arrivalLocation)
                && this.departureTime.equals(departureDate)
                && availableSeats > 0;
    }

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }

}
