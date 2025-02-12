package com.example.Student_Management_System.service;


import com.example.Student_Management_System.ResourceNotFoundException;
import com.example.Student_Management_System.entity.Course;
import com.example.Student_Management_System.entity.Student;
import com.example.Student_Management_System.repository.CourseRepository;
import com.example.Student_Management_System.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Course updateCourse(Long id, Course updatedCourse) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        course.setCourseName(updatedCourse.getCourseName());
        course.setDescription(updatedCourse.getDescription());
        course.setCourseType(updatedCourse.getCourseType());
        course.setDuration(updatedCourse.getDuration());

        return courseRepository.save(course);
    }

    public Course assignCourseToStudent(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + courseId));
        Student student=studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student);
        return course;
    }

    public List<Student> getStudentsByCourseId(Long courseId) {
        return studentRepository.findByCoursesId(courseId);
    }

    public Course addNewCourse(Course course) {
        return courseRepository.save(course);
    }
}
