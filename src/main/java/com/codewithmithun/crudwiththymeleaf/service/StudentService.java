package com.codewithmithun.crudwiththymeleaf.service;

import com.codewithmithun.crudwiththymeleaf.entities.Address;
import com.codewithmithun.crudwiththymeleaf.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    Student updateStudent(Student student);
    void deleteStudentById(Long id);
    public Page<Student> getAllStudents(Pageable pageable);
    public Page<Student> searchStudents(String keyword, Pageable pageable);
}
