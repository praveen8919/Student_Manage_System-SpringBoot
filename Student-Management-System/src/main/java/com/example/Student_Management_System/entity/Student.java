package com.example.Student_Management_System.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    private String gender;
    private String uniqueStudentCode;
    private String email; // Add this field
    private String mobileNumber; // Add this field
    private String parentsName; // Add this field

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<StudentAddress> addresses;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonManagedReference
    private List<Course> courses;


    public Student() {
    }

    public Student(Long id, String name, Date dateOfBirth, String gender, String uniqueStudentCode, String email, String mobileNumber, String parentsName, List<StudentAddress> addresses, List<Course> courses) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.uniqueStudentCode = uniqueStudentCode;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.parentsName = parentsName;
        this.addresses = addresses;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUniqueStudentCode() {
        return uniqueStudentCode;
    }

    public void setUniqueStudentCode(String uniqueStudentCode) {
        this.uniqueStudentCode = uniqueStudentCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getParentsName() {
        return parentsName;
    }

    public void setParentsName(String parentsName) {
        this.parentsName = parentsName;
    }

    public List<StudentAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<StudentAddress> addresses) {
        this.addresses = addresses;
    }


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }




}