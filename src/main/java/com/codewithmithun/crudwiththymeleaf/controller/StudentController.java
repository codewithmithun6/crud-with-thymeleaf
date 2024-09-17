package com.codewithmithun.crudwiththymeleaf.controller;

import com.codewithmithun.crudwiththymeleaf.entities.Student;
import com.codewithmithun.crudwiththymeleaf.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/students")
    public String listStudents(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(defaultValue = "") String keyword){

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage;


        // If search is not empty, search the students by name or email
        if (keyword != null && !keyword.isEmpty()) {
            studentPage = studentService.searchStudents(keyword, pageable);
        } else {
            studentPage = studentService.getAllStudents(pageable);
        }

        model.addAttribute("students", studentPage.getContent());  // Current page data
        model.addAttribute("currentPage", page);                   // Current page number
        model.addAttribute("totalPages", studentPage.getTotalPages()); // Total pages
        model.addAttribute("search", keyword);

        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {

        // create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";

    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {

        // get student from database by id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setParentsMobile(student.getParentsMobile());

        // save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    // handler method to handle delete student request

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

//    @GetMapping
//    public String viewHomePage(
//            @RequestParam(value = "search", required = false) String search,
//            @RequestParam(value = "page", defaultValue = "1") int page,
//            Model model) {
//
//        int pageSize = 10; // Number of items per page
//        Page<Student> studentPage;
//
//        // If search is not empty, search the students by name or email
//        if (search != null && !search.isEmpty()) {
//            studentPage = studentService.searchStudents(search, page, pageSize);
//        } else {
//            // Otherwise return all students
//            studentPage = studentService.getAllStudents(page, pageSize);
//        }
//
//        model.addAttribute("studentPage", studentPage);
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", studentPage.getTotalPages());
//        model.addAttribute("search", search);
//
//        return "students";
//    }

}
