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
public class Passenger {

    private String Name;
    private String Phone;
    private String Email;
    private String SelectedPlane;

    public Passenger() {
    }

    public Passenger(String Name, String Phone, String Email) {
        this.Name = Name;
        this.Phone = Phone;
        this.Email = Email;
    }

    public Passenger(String Name, String Phone, String Email, String SelectedPlane) {
        this.Name = Name;
        this.Phone = Phone;
        this.Email = Email;
        this.SelectedPlane = SelectedPlane;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSelectedPlane() {
        return SelectedPlane;
    }

    public void setSelectedPlane(String SelectedPlane) {
        this.SelectedPlane = SelectedPlane;
    }

    @Override
    public String toString() {
        return "Passenger{" + "Name: " + Name + ", Phone: " + Phone + ", Email:" + Email + ", SelectedPlane: " + SelectedPlane + '}';
    }
}
