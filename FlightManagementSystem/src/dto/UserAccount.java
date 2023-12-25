/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import utils.Utils;

/**
 *
 * @author Nguyen Trung Nam
 */
public class UserAccount extends HashMap<String, User> implements I_UserAccount{

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String username = parts[0];
                    String role = parts[1];

                    User user = new User(username, role);
                    this.put(username, user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading user accounts: " + e.getMessage());
        }
    }
    
    @Override
    public String add() {
        String username = Utils.getUsername("Enter username: ", "Username cannot be duplicated", 1, this);
        String role = Utils.getString("Enter role of username: ", false);

        User u = new User(username, role);

        this.put(username, u);
        return username;
    }

    @Override
    public boolean check(String username) {
        
        User user = this.get(username);

        if (user != null && user.getRole().equals("admin")) {
            // Người dùng là quản trị viên, cho phép truy cập các tính năng đặc quyền.
            return true;
        } else {
            // Người dùng không phải là quản trị viên, không cho phép truy cập các tính năng đặc quyền.
            return false;
        }
    }
}
