package com.example.Student_Management_System.service;

import com.example.Student_Management_System.ResourceNotFoundException;
import com.example.Student_Management_System.entity.Course;
import com.example.Student_Management_System.entity.Student;
import com.example.Student_Management_System.repository.CourseRepository;
import com.example.Student_Management_System.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public Student admitStudent(Student student) {
        return studentRepository.save(student);
    }



    @Transactional
    public Student updateStudentProfile(Long id, Student updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        student.setName(updatedStudent.getName());
        student.setDateOfBirth(updatedStudent.getDateOfBirth());
        student.setGender(updatedStudent.getGender());
        student.setUniqueStudentCode(updatedStudent.getUniqueStudentCode());
        student.setEmail(updatedStudent.getEmail());
        student.setMobileNumber(updatedStudent.getMobileNumber());
        student.setParentsName(updatedStudent.getParentsName());

        if (updatedStudent.getAddresses() != null) {
            student.setAddresses(updatedStudent.getAddresses());
        }

        try {
            return studentRepository.save(student);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new ConcurrentModificationException("The entity was updated by another transaction.");
        }
    }

    public List<Student> getStudentByName(String name) {
        return studentRepository.findByNameContaining(name);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Course> getCoursesByStudentId(Long studentId) {
        return courseRepository.findByStudentsId(studentId);
    }

    public String removeCourseFromStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));

        student.getCourses().remove(course);
        course.getStudents().remove(student);

        studentRepository.save(student);
        courseRepository.save(course);

        return "Student with ID " + studentId + " has been removed from Course ID " + courseId;
    }


    public Student validateAndGetStudent(String studentCode, String dateOfBirth) throws ResourceNotFoundException {
        
    	Student student = studentRepository.findByUniqueStudentCode(studentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with code: " + studentCode));

        System.out.println("Student found: " + student.getUniqueStudentCode() + " - DOB in DB: " + student.getDateOfBirth());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dob = dateFormat.parse(dateOfBirth);
            System.out.println("Received DOB: " + dateFormat.format(dob));

            if (!dateFormat.format(student.getDateOfBirth()).equals(dateFormat.format(dob))) {
                System.out.println("Mismatch: DB DOB = " + dateFormat.format(student.getDateOfBirth()) + 
                                   ", Received DOB = " + dateFormat.format(dob));
                throw new IllegalArgumentException("Invalid student code or date of birth.");
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use 'yyyy-MM-dd'.");
        }

        return student;
    }


    public Student getStudentByStudentCode(String studentCode) throws ResourceNotFoundException {
        return studentRepository.findByUniqueStudentCode(studentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with code: " + studentCode));
    }
}
