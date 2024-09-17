package com.codewithmithun.crudwiththymeleaf.service.impl;

import com.codewithmithun.crudwiththymeleaf.entities.Student;
import com.codewithmithun.crudwiththymeleaf.repositiries.StudentRepository;
import com.codewithmithun.crudwiththymeleaf.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        super();
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

//    public Page<Student> getAllStudents(int page, int pageSize) {
//        Pageable pageable = PageRequest.of(page - 1, pageSize);
//        return studentRepository.findAll(pageable);
//    }
//
//    public Page<Student> searchStudents(String keyword, int page, int pageSize) {
//        Pageable pageable = PageRequest.of(page - 1, pageSize);
//        return studentRepository.findByFirstNameContainingOrLastNameContainingOrEmailContainingOrParentsMobileContaining(
//                keyword, keyword,keyword, keyword, pageable);
//    }

//    public Page<Student> getAllStudents(Pageable pageable) {
//        return studentRepository.findAll(pageable);
//    }

    public Page<Student> searchStudents(String keyword, Pageable pageable) {
        return studentRepository.findByFirstNameContainingOrLastNameContainingOrEmailContainingOrParentsMobileContaining(
                keyword, keyword, keyword,keyword, pageable);
    }

}
