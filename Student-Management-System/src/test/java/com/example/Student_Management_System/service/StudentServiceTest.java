package com.example.Student_Management_System.service;


import com.example.Student_Management_System.entity.Student;
import com.example.Student_Management_System.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testAdmitStudent() {
        Student student = new Student();
        student.setName("John Doe");

        when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = studentService.admitStudent(student);
        assertNotNull(savedStudent);
        assertEquals("John Doe", savedStudent.getName());
    }
}