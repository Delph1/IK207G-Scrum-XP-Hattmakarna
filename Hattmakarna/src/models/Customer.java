/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author Petra Schröder
 */
public class Customer {

    private int customer_id;
    private String firstName;
    private String lastName;
    private String streetName;
    private String postal_code;
    private String postal_city;
    private String state;
    private String country;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> emails;

    public Customer(int customer_id, String firstName, String lastName, String streetName, String postal_code, String postal_city, String state, String country,ArrayList<String> phoneNumbers,ArrayList<String> emails) {
        this.customer_id = customer_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.postal_code = postal_code;
        this.postal_city = postal_city;
        this.state = state;
        this.country = country;
        this.phoneNumbers = phoneNumbers;
        this.emails = emails;
    }

    public int getId() {
        return customer_id;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getLastName() {
        return lastName;
    }
    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers=phoneNumbers;
    }
    public void setEmail(ArrayList<String> emails) {
        this.emails=emails;
    }
    public ArrayList<String> getEmails() {
        return emails;
    }
    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }
    
    public void setStreetName(String streetname) {
        this.streetName = streetname;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setPostalCode(String postalCode) {
        this.postal_code = postalCode;
    }

    public String getPostalCode() {
        return postal_code;
    }

    public void setPostalCity(String postalCity) {
        this.postal_city = postalCity;
    }

    public String getPostalCity() {
        return postal_city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

}
