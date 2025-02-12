package com.example.Student_Management_System.controller;

import com.example.Student_Management_System.ResourceNotFoundException;
import com.example.Student_Management_System.entity.Course;
import com.example.Student_Management_System.entity.Student;
import com.example.Student_Management_System.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping("/admin/getStudentByName/{name}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable String name) {
        List<Student> students = studentService.getStudentByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/admin/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping("/admin/admitStudent")
    public ResponseEntity<?> admitStudent(@RequestBody Student student) {
        try {
            Student newStudent = studentService.admitStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while admitting the student: " + e.getMessage());
        }
    }

    @PutMapping("/student/updateProfile")
    public ResponseEntity<?> updateStudentProfile(
            @RequestParam String studentCode,
            @RequestParam String dateOfBirth,
            @RequestBody Student studentDetails) {
        try {
            Student student = studentService.validateAndGetStudent(studentCode, dateOfBirth);
            Student updatedStudent = studentService.updateStudentProfile(student.getId(), studentDetails);
            return ResponseEntity.ok(updatedStudent);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating profile: " + e.getMessage());
        }
    }

    @GetMapping("/student/getStudentCourses")
    public ResponseEntity<?> getStudentCourses(
            @RequestParam String studentCode,
            @RequestParam String dateOfBirth) {
        try {
            Student student = studentService.validateAndGetStudent(studentCode, dateOfBirth);
            List<Course> courses = studentService.getCoursesByStudentId(student.getId());
            return ResponseEntity.ok(courses);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving courses: " + e.getMessage());
        }
    }

    @DeleteMapping("/student/removeCourse")
    public ResponseEntity<?> removeStudentFromCourse(
            @RequestParam String studentCode,
            @RequestParam String dateOfBirth,
            @RequestParam Long courseId) {
        try {
            Student student = studentService.validateAndGetStudent(studentCode, dateOfBirth);
            String message = studentService.removeCourseFromStudent(student.getId(), courseId);
            return ResponseEntity.ok(message);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing course: " + e.getMessage());
        }
    }
}
