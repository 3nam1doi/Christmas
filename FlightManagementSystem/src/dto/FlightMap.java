/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Nguyen Trung Nam
 */
public class FlightMap extends HashMap<String, SeatMap> implements I_FlightMap {

    public FlightMap() {

    }

    @Override
    public void initializeSeatMap(String flightNumber, int rows) {
        SeatMap seatMap = new SeatMap(); 
        for (int row = 1; row <= (rows + 1); row++) {
            for (int seatNum = 1; seatNum <= 6; seatNum++) {
                String seatLabel = row + String.valueOf((char) ('A' + seatNum - 1));
                seatMap.put(seatLabel, "O");
            }
        }
        // Thêm bản đồ ghế của chuyến bay vào FlightMap
        this.put(flightNumber, seatMap);
    }

    @Override
    public void showSeatMap(String flightNumber, int rows) {
        System.out.println("[Seat Map for Flight " + flightNumber + "]");
        System.out.println("  O: Available || X: Seated");

        // Tiêu đề cột
        System.out.print("     "); 
        for (char seat = 'A'; seat < (char) ('A' + 6); seat++) {
            System.out.print(String.format("%-4s", seat));
        }
        System.out.println(); 

        for (int row = 1; row <= ((rows/6) + 1); row++) {
            System.out.print(String.format("%-4d", row)); 
            for (int seatNum = 1; seatNum <= 6; seatNum++) {
                String seatLabel = row + String.valueOf((char) ('A' + seatNum - 1));
                String status = this.get(flightNumber).get(seatLabel);
                System.out.print("[" + status + "] ");
            }
            System.out.println(); 
        }
    }

    @Override
    public void choose(String flightNumber, String seat) {
        this.get(flightNumber).put(seat, "X");
        System.out.println("Seat " + seat + " has been booked successfully.");
    }

    public void remove(String flightNumber, String seat) {
        this.get(flightNumber).put(seat, "O");
    }

    public String getRandomAvailableSeat(String flightNumber) {
        List<String> availableSeats = new ArrayList<>();

        if (this.get(flightNumber) != null) {
            for (Map.Entry<String, String> entry : this.get(flightNumber).entrySet()) {
                if (entry.getValue().equals("O")) {
                    availableSeats.add(entry.getKey());
                }
            }
        }

        // Kiểm tra nếu không còn chỗ ngồi trống
        if (availableSeats.isEmpty()) {
            System.out.println("No available seats left.");
            return null;
        }

        // Chọn ngẫu nhiên một chỗ ngồi từ danh sách và loại bỏ nó khỏi danh sách
        Random random = new Random();
        int randomIndex = random.nextInt(availableSeats.size());
        String selectedSeat = availableSeats.get(randomIndex);
        availableSeats.remove(randomIndex);

        return selectedSeat;
    }

    public boolean check(String flightNumber, String seat) {
        String status = this.get(flightNumber).get(seat);
        if (status != null && status.equals("O")) {
            return true; // Chỗ ngồi còn trống
        } else if (status != null && status.equals("X")) {
            System.out.println("Seat " + seat + " has already been taken.");
            return false; // Chỗ ngồi đã được đặt
        } else {
            System.out.println("Invalid seat " + seat + ". Please choose a valid seat.");
            return false; // Chỗ ngồi không hợp lệ
        }
    }

}
