/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.Scanner;

/**
 *
 * @author Nguyen Trung Nam
 */
public class Administrator implements I_Administrator {
    
    FlightManagement flights = new FlightManagement();
    CrewAssigntment crews = new CrewAssigntment(flights);

    public Administrator( FlightManagement flights, CrewAssigntment crews) {
        this.flights = flights;
        this.crews = crews;
    }

    @Override
    public void addCrew() {
        crews.add();
    }

    @Override
    public void updateCrew() {
        crews.update();
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID of flight: ");
        String flightNumber = scanner.nextLine();
        crews.show(flightNumber);
    }
    
}
