package com.example.Student_Management_System.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String description;
    private String courseType;
    private int duration;


    @ManyToMany(mappedBy = "courses")
    @JsonBackReference
    private List<Student> students;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Topic> topics;


    public Course() {
    }

    public Course(Long id, String courseName, String description, String courseType, int duration, List<Student> students, List<Topic> topics) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.courseType = courseType;
        this.duration = duration;
        this.students = students;
        this.topics = topics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }



    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}