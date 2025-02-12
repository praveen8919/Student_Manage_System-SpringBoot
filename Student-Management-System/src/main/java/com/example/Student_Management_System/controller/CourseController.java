package com.example.Student_Management_System.controller;


import com.example.Student_Management_System.ResourceNotFoundException;
import com.example.Student_Management_System.entity.Course;
import com.example.Student_Management_System.entity.Student;
import com.example.Student_Management_System.repository.CourseRepository;
import com.example.Student_Management_System.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        try {
            Course course = courseService.updateCourse(id, updatedCourse);
            return ResponseEntity.ok(course);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Course> addNewCourse(@RequestBody Course course) {
        Course newCourse = courseService.addNewCourse(course);
        return ResponseEntity.ok(newCourse);

    }

    @PostMapping("/assign")
    public Course assignCourseToStudent(@RequestParam Long courseId, @RequestParam Long studentId) {
            return courseService.assignCourseToStudent(courseId, studentId);
    }

    @GetMapping("/getAllStudentsAssignedToCourse/{courseId}")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable Long courseId) {
        List<Student> students = courseService.getStudentsByCourseId(courseId);
        return ResponseEntity.ok(students);
    }
}