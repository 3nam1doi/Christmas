/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Nguyen Trung Nam
 */
public interface I_FlightManagement {
    public void add();
    
    public boolean isMatch(String flightNumber, String departureLocation, String arrivalLocation, String departureDate);
    
    public void bookSeat(String flightNumber);
}
