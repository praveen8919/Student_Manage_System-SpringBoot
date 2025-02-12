package com.example.Student_Management_System.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class StudentAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String area;
    private String state;
    private String district;
    private String pincode;
    private String addressType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;


    public StudentAddress() {
    }

    public StudentAddress(Long id, String area, String state, String district, String pincode, String addressType, Student student) {
        this.id = id;
        this.area = area;
        this.state = state;
        this.district = district;
        this.pincode = pincode;
        this.addressType = addressType;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


}