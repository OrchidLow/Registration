/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.time.DayOfWeek;
import java.time.LocalTime;
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
 *  ~ COURSE CLASS ~
 * Course class creates a course and allows edits where needed
 * 
 */
public class Course {
    
    ArrayList<Student> studentList = new ArrayList<Student>();
    
    // Declare the variables 
    private boolean matureClass = false;
    
    private Instructor courseInstructor;
    private String courseCode, courseName, classroom, prerequisiteCourse; 
    // variable for return values
    private String rtn;
    private DayOfWeek classWeekday;
    private LocalTime classTime;
    private int classSize;
    
    // Build first constructor. Course without a prerequisite.
    public Course(Instructor instr,
                  String courseCode,
                  String courseName,
                  String room,
                  DayOfWeek classDay,
                  LocalTime time,
                  int size)
    {        
        courseInstructor = instr;
        this.courseCode = courseCode;
        this.courseName =  courseName;
        classroom = room;
        classWeekday = classDay;
        classTime = time;
        classSize = size;
        
        // Checks time class starts and ensures it is in the correct time slots
        if (classTime.isBefore(LocalTime.parse("08:00")) || classTime.isAfter(LocalTime.parse("18:00"))) {
            throw new java.lang.IllegalArgumentException("Course start time must be between 08:00-18:00");
        }
        
        // Test to ensure invalid instructor will not be added
        if (!instr.instructorCanTeach(courseCode)) {
            throw new java.lang.IllegalArgumentException("Professor "+instr.getFirstName()+" "+instr.getLastName()+" is not qualified to teach COMP9999");
        }
    }
     
    // New consrtuctor for course with prerequisite
    public Course(Instructor instr,
                  String courseCode,
                  String courseName,
                  String room,
                  DayOfWeek classDay,
                  LocalTime time,
                  int size,
                  String prerequisite)
    {        
        courseInstructor = instr;
        this.courseCode = courseCode;
        this.courseName =  courseName;
        classroom = room;
        classWeekday = classDay;
        classTime = time;
        classSize = size;
        prerequisiteCourse = prerequisite;
        
        // Checks time class starts and ensures it is in the correct time slots
        if (classTime.isBefore(LocalTime.parse("08:00")) || classTime.isAfter(LocalTime.parse("18:00"))) {
            throw new java.lang.IllegalArgumentException("Course start time must be between 08:00-18:00");
        }
        
        // Test to ensure invalid instructor will not be added
        if (!instr.instructorCanTeach(courseCode)) {
            throw new java.lang.IllegalArgumentException("Professor "+instr.getFirstName()+" "+instr.getLastName()+" is not qualified to teach COMP9999");
        }
    }
    
    // Getters
    public Instructor getCourseInstructor() {
        return courseInstructor;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    // Get the classroom code
    public String getClassRoom() {
        return classroom;
    }
    public DayOfWeek getClassWeekday() {
        return classWeekday;
    }
    public LocalTime getClassTime() {
        return classTime;
    }
    public int getClassSize() {
        return classSize;
    }
    
    // Setters
    public void setInstructor(Instructor instr) {
        courseInstructor = instr;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setClassRoom(String room) {
        classroom = room;
    }
    public void setClassWeekday(DayOfWeek classDay) {
        classWeekday = classDay;
    }
    public void setClassTime(LocalTime time) {
        classTime = time;
    }
    
    // Set class size max
    public String setClassSize(int size) {
        // Class size can not go over 40
        if(size < 40) {
            classSize = size;
        }
        else {
            classSize = 40;
        }
        return "Max class size = " +classSize+ ", it has been set to " +classSize;
    }
    
    // Get the class day and time
    public String getCourseDayAndTime() {
        return (getClassWeekday()+"'s, starting at "+getClassTime());
    }
    
    // Get the instructor that is teaching the class
    public Instructor getInstructorToTeach() {
        return courseInstructor;
    }
    
    // Add student into a class
    public String addStudent(Student student) {
        
        // Checks to see if course has a prerequisite course
        if (this.prerequisiteCourse == null) {

            // Student can not be added if not in good standing
            if(student.studentInGoodStanding() == false) {
                rtn = "The Student is not in good standing and cannot join the course.";
            }
            // Checks class size. If there is no space then do not add.
            else if(classSize <= studentList.size()) { 
                rtn = "Student was not added because the course is full";
            }
            // Add student to the class if possible.
            else {
                studentList.add(student);
            }
        }
        // if there is a prerequisite then run this code
        else {
            if (!student.getCoursesCompleted().contains(this.prerequisiteCourse)) {
                rtn = "Student has not completed the prerequisite course: "+this.prerequisiteCourse;
            }
            else if(student.studentInGoodStanding() == false) {
                rtn = "The Student is not in good standing and cannot join the course.";
            }
            // Checks class size. If there is no space then do not add.
            else if(classSize <= studentList.size()) {  
                rtn = "Student was not added because the course is full";
            }
            // Add student to the class if possible.
            else {                                      
                studentList.add(student);
            }
        }
        return rtn;
    }
    
    // Sets the class to mature class with boolean.
    public boolean matureClass() {
        int ageAverage = 0;                         // Declare new variable
        
        for(Student student : studentList) {        // Advanced for loop to get ages of students
            ageAverage+= student.getStudentAge();   // Add all student ages in array list and put it into a variable
        }
        
        if (ageAverage/studentList.size() >= 25) {  // Averages the ages to determine if class is mature
            return true;
        }
        return false;                               // Returns false
    }
    
    // Display the students in the class
    public String displayTheClassList() {
        if (studentList.isEmpty()) {                // Checks if list is empty
            rtn = "There are no students registered in this class yet.";
        }
        else {
            StringBuilder builder = new StringBuilder(); // Built a string
            for (Student value : studentList) {
                builder.append(value);
            }
            rtn = builder.toString();
        }
        return rtn;
    }
    
    // Get the prerequisite course code
    public String checkPrerequisite() {
        return prerequisiteCourse;
    }
    
    // toString to return course information
    @Override
    public String toString() {
        return(getCourseCode()+"-"+getCourseName());
    }
}