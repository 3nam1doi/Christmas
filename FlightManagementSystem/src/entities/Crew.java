/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Nguyen Trung Nam
 */
public class Crew {
    private String name;
    private String flightNumber;
    private String role;

    public Crew(String name, String flightNumber, String role) {
        this.name = name;
        this.flightNumber = flightNumber;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Crew{" + "name=" + name + ", flightNumber=" + flightNumber + ", role=" + role + '}';
    }

}
