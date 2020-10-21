/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
 *  ~ STUDENT CLASS ~
 * Student class is a child class of Members
 * It is used to create student objects and edit them if needed.
 * 
 */

public class Student extends Members{ // Inherits from Members class
    
    // Make an hash map for courses completed
    HashMap<Course, String> coursesCompleted = new HashMap<Course, String>();
        
    // Declare variables.
    private String programCode;
    private int studentNumber;
    private LocalDate enrollmentDate;
    
    private boolean inGoodStanding = true;
    private boolean hasPassed = true;
    
    // Create consrtuctor
    public Student(String fName, 
                   String lName, 
                   String strtAdd, 
                   String cty, 
                   String postCode, 
                   String prgCode, 
                   int stNum, 
                   LocalDate enrollDate, 
                   LocalDate dob) 
    {
        super(fName, lName, strtAdd, cty, postCode, dob);
        
        // Check age of student to ensure they are under the age of 100, if so throw illegal argument
        if (Period.between(dob, currentDate).getYears() > 100 ) {
            throw new java.lang.IllegalArgumentException("Please check the year entered, student cannot be over 100 years old");
        }
        
        programCode = prgCode;
        studentNumber = stNum;
        enrollmentDate = enrollDate;
    }
    
    // Getters
    public String getProgramCode() {
        return programCode;
    }
    public int getStudentNumber(){
        return studentNumber;
    }
    // Get the full date (yyyy/mm/dd)
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    } // getters end
    
    // Setters
    public void setProgramCode(String prgCode) {
        programCode = prgCode;
    }
    public void setStudentNumber(int stNum) {
        studentNumber = stNum;
    }
    public void setEnrollmentDate(LocalDate enrollDate) {
        enrollmentDate = enrollDate;
    } // setters end
    
    // Get student's age
    public int getStudentAge() {
        // Use date of birth and current date from parent class and get the difference
        return Period.between(getDateOfBirth(), currentDate).getYears();
    }
    
    // Get student's birthday
    public LocalDate getStudentBirthday() {
        return getDateOfBirth();
    }
    
    // Get the student's address by using the getters from the parent class.
    public String getStudentAddress() {
        return (getStreetAddress()+" "+getCity()+" "+getPostalCode());
    }
    
    // Check to see if student is in good standings
    public boolean studentInGoodStanding()  {
        return inGoodStanding;                // Defualt set to true
    }
    
    // Check to see if student is suspended
    public boolean suspendStudent()  {
         inGoodStanding = false;              // Set inGoodStanding to false
         return inGoodStanding;
    }
    
    // Reinstate the student
    public boolean reinstateStudent() {
        inGoodStanding = true;               // Set inGoodStanding to true
        return inGoodStanding;
    }
    
    // Get number of years a student is enrolled
    public int getNoOfYearEnrolled() {
        return Period.between(getEnrollmentDate(), currentDate).getYears();
    }
    
    // Get only the year enrolled, not the full date
    public int getYearEnrolled() {
        return this.enrollmentDate.getYear();
    }
    
    // add completed courses the students
    public void addCompletedCourse(Course courseCode, int grade) {
        // Check to see if student passed course with valid grade
        if (grade > 50 && grade < 100) {
            // add course
            coursesCompleted.put(courseCode, String.valueOf(grade));
            // set boolean to true
            hasPassed = true;
        }
        // if grade is not a valid value, throw illegal argument
        else if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("grade must be 0-100 inclusive");
        }
        else {
            hasPassed = false;
        }
    }
    
    // Get the courses a student has completed 
    public String getCoursesCompleted() {

        ArrayList<String> newList = new ArrayList<String>();
        
        // Enhanced for loop
        for(Map.Entry<Course, String> entry : coursesCompleted.entrySet())
        {
            // Build string for ouuput
            newList.add(entry.getKey().getCourseCode() + "-" + entry.getKey().getCourseName() + " grade=" + entry.getValue());
        }
        return ("["+String.join(",", newList)+"]");
    }
    
    // Checks if student has completed course
    public Boolean hasCompleted(String courseCode) {
        return hasPassed;
    }
    
    // toString to return student information
    @Override
    public String toString() {
        return(getFirstName()+" "+getLastName()+", "+"student number: "+getStudentNumber());
    }
}
