/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.time.DayOfWeek;
import java.time.LocalTime;

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
 *  ~ COURSEWITHLAB CLASS ~
 * Inherits from Course class,
 * Creates courses with labs.
 * 
 */

public class CourseWithLab extends Course {
    
    // Delcare new variables
    private Instructor labInstructor;
    private String labClassroom;
    private DayOfWeek labDay;
    private LocalTime labTime;
    
    // Build first constructor. CourseWithLab without a prerequisite.
    public CourseWithLab(Instructor instr,
                         String courseCode,
                         String courseName,
                         String room,
                         DayOfWeek classDay,
                         LocalTime time,
                         int size,
                         Instructor labInstr,
                         String labRoom,
                         DayOfWeek labDay,
                         LocalTime labTime)
    {
        super(instr, courseCode, courseName, room, classDay, time, size);
            
        labInstructor = labInstr;
        labClassroom = labRoom;
        this.labDay = labDay;
        this.labTime = labTime;      
            
        // Test to ensure invalid lab tech will not be added
        if (!labInstr.instructorCanTeach(courseCode+"-LAB")) {
            throw new java.lang.IllegalArgumentException("The Lab Tech is not qualified to host the lab");
        }
        
         // Checks time class starts and ensures it is in the correct time slots
        if (labTime.isBefore(LocalTime.parse("08:00")) || labTime.isAfter(LocalTime.parse("18:00"))) {
            throw new java.lang.IllegalArgumentException("The lab start time must be between 08:00-18:00");
        }
    }
    
    // New consrtuctor for CourseWithLab with prerequisite
    public CourseWithLab(Instructor instr,
                         String courseCode,
                         String courseName,
                         String room,
                         DayOfWeek classDay,
                         LocalTime time,
                         int size,
                         String prerequisite,
                         Instructor labInstr,
                         String labRoom,
                         DayOfWeek labDay,
                         LocalTime labTime)
    {
        super(instr, courseCode, courseName, room, classDay, time, size, prerequisite);
            
        labInstructor = labInstr;
        labClassroom = labRoom;
        this.labDay = labDay;
        this.labTime = labTime;      
            
        // Test to ensure invalid lab tech will not be added
        if (!labInstr.instructorCanTeach(courseCode+"-LAB")) {
            throw new java.lang.IllegalArgumentException("The Lab Tech is not qualified to host the lab");
        }
        
         // Checks time class starts and ensures it is in the correct time slots
        if (labTime.isBefore(LocalTime.parse("08:00")) || labTime.isAfter(LocalTime.parse("18:00"))) {
            throw new java.lang.IllegalArgumentException("The lab start time must be between 08:00-18:00");
        }
    }
    
    // Getters
    public Instructor getLabTech() {
        return labInstructor;
    }
    public String getLabClassroom() {
        return labClassroom;
    }
    public DayOfWeek getLabDay() {
        return labDay;
    }
    public LocalTime getLabTime() {
        return labTime;
    }
    
    
    // Setters
    public void setLabTech(Instructor labInstr) {
        labInstructor = labInstr;
    }
    public void setLabClassroom(String labRoom) {
        labClassroom = labRoom;
    }
    public void setLabDay(DayOfWeek labDay) {
        this.labDay = labDay;
    }
    public void setLabDay(LocalTime labTime) {
        this.labTime = labTime;
    }
    
    // Get the lab class and time
    public String getLabClassAndTime() {
        return ("room: "+getLabClassroom()+", "+getLabDay()+" starting at "+getLabTime());
    }
    
    // toString to return course with lab information
    @Override
     public String toString() {
        return(getCourseCode()+"-"+getCourseName()+" with lab");
    }
}
