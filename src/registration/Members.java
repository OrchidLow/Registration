/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.time.LocalDate;

/**
 *
 *  @author Lani

 * 
 * ~ ASSIGNMENT 2 ~
 * Name: Lan Li Low
 * Student ID: 200440104
 * Date: 04/07/2020
 * Class: Intro to Object Oriented Programming with Java
 * 
 * 
 *  ~ MEMBERS CLASS ~
 * This class is the parent class for Student and Instructor class. 
 * Since some variables are used by both classes, it is easier to make a parent
 * class that holds the variables instead of each class having their own.
 * 
 */

public class Members {
    // Declare variables.
    public LocalDate currentDate = LocalDate.now();
    
    private String firstName, lastName, streetAddress, city, postalCode;
    private LocalDate dateOfBirth;
    
    // Build a consrtuctor
    public Members(String fName, 
                   String lName, 
                   String strtAdd, 
                   String cty, 
                   String postCode, 
                   LocalDate dob) 
    {
    firstName   = fName;
    lastName = lName;
    streetAddress = strtAdd;
    city = cty;
    postalCode = postCode;
    dateOfBirth = dob;
    }
    
    // Getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public String getCity() {
        return city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    // Setters
    public void setFirstName(String fName) {
         firstName = fName;
    }
    public void setLastName(String lName) {
         lastName = lName;
    }
    public void setStreetAddress(String strtAdd) {
        streetAddress = strtAdd;
    }
    public void setCity(String cty) {
        city = cty;
    }
    public void setPostalCode(String postCode) {
        postalCode = postCode;
    }
    public void setBirthday(LocalDate dob) {
        dateOfBirth = dob;
    }
    
    // Changes the address
    public void changeAddress(String strtAdd, String cty, String postCode) {
        streetAddress = strtAdd;
        city = cty;
        postalCode = postCode;
    }
}