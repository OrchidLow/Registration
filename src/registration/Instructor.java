/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

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
 *  ~ INSTRUCTOR CLASS ~
 * Instructor class is a child class of Members.
 * It is used to create instructor objects and edit them if needed.
 * 
 */

public class Instructor extends Members{
    
    // Make an array list for courses
    ArrayList<String> course = new ArrayList<String>();
    
    // Declare variables.
    private String rtn;     // Variable for returns
    private int instructorNumber;
    private LocalDate employmentDate;
    
    public Instructor(String fName, 
                      String lName, 
                      int instructNum,
                      String strtAdd, 
                      String cty, 
                      String postCode, 
                      LocalDate empDate, 
                      LocalDate dob) 
    {
        super(fName, lName, strtAdd, cty, postCode, dob);
        
        // test age of teacher to see if they are too old
        if (Period.between(dob, currentDate).getYears() > 100 ) {
            throw new java.lang.IllegalArgumentException("Please check the year entered, instructor cannot be over 100 years old");
        }
        
        // test for invalid hire date
        if (Period.between(empDate, currentDate).getYears() > 80 ) {
            throw new java.lang.IllegalArgumentException(empDate+" as a hire date would mean Anita started working over 80 years ago");
        }
        
        instructorNumber = instructNum;
        employmentDate = empDate;
    }
    
    // Getters
    public int getInstructorNumber() {
        return instructorNumber;
    }
    public LocalDate getEmploymentDate() {
        return employmentDate;
    }
    
    // Setters
    public void setInstructorNumber(int instructNum) {
        instructorNumber = instructNum;
    }
    public void setEmplymentDate(LocalDate empDate) {
        employmentDate = empDate;
    }
    
    // Gets the age of the instructor
    public int getAgeInYears() {
        return Period.between(getDateOfBirth(), currentDate).getYears();
    }
    
    // Calculate the number of years an instructor has been at the school
    public int noOfYearsAtCollege() {
        return Period.between(getEmploymentDate(), currentDate).getYears();
    }
    
    // Get the instructor's address
    public String getInstructorAddress() {
        return (getStreetAddress()+", "+getCity()+", "+getPostalCode());
    }
    
    // returns what subjects a teacher is certified to teach
    public String listOfSubjectsCertifiedToTeach() {
        // if the list is empty
        if (course.isEmpty()) {
            rtn = "not qualified to teach courses yet.";
        }
        // returns the list of courses
        else {
            rtn = course.toString();
        }
        return rtn; 
    }
    
    // add courses the teacher can teach
    public void addCourseToInstructorAbilities(String classes) {
        // Ensures the same class can not be added more than once
        if (!course.contains(classes)) {
            course.add(classes);
        }
    }
    
    // Test to see if instructor can teach a course
    public boolean instructorCanTeach(String classes) {
        return course.contains(classes);
    }
    
    // toString to return instructor information
    @Override
    public String toString() {
        return(getFirstName()+" "+getLastName());
    }
}

