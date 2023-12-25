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
public interface I_FlightMap {
    
    public void initializeSeatMap(String s, int rows);
    
    public void choose(String flightNumber, String seat);
    
    public void showSeatMap(String s, int rows);
}
