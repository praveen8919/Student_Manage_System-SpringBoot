package com.example.Student_Management_System.repository;


import com.example.Student_Management_System.entity.Course;
import com.example.Student_Management_System.entity.Student;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameContaining(String name);



    List<Student> findByCoursesId(Long courseId);



    Optional<Student> findByUniqueStudentCode(String uniqueStudentCode);
}