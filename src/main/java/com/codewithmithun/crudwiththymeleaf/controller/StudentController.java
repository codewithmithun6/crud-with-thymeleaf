package com.codewithmithun.crudwiththymeleaf.controller;

import com.codewithmithun.crudwiththymeleaf.entities.Address;
import com.codewithmithun.crudwiththymeleaf.entities.Student;
import com.codewithmithun.crudwiththymeleaf.service.AddressService;
import com.codewithmithun.crudwiththymeleaf.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AddressService addressService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

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
    // view student
    @GetMapping("/students/view/{studentId}")
    public String viewStudentForm(@PathVariable Long studentId, Model model) {

        LOGGER.info("Student: "+studentService.getStudentById(studentId));
        LOGGER.info("address: "+addressService.getAddressByStudentId(studentId));
        model.addAttribute("student", studentService.getStudentById(studentId));
        model.addAttribute("address", addressService.getAddressByStudentId(studentId));
        return "view_student";
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
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setParentsMobile(student.getParentsMobile());
        existingStudent.setGender(student.getGender());
        existingStudent.setDob(student.getDob());

        // save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    // handler method to handle delete student request

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        LOGGER.info("Delete button triggered");

        // Fetch the address associated with the student, might be null
        Address studentAddress = addressService.getAddressByStudentId(id);

        // Check if studentAddress is null or if student is null before accessing getId
        if (studentAddress != null && studentAddress.getStudent() != null) {
            LOGGER.info("student Address: " + studentAddress.getStudent().getId());

            // Safely compare ids and handle the deletion
            if (id != null && id.equals(studentAddress.getStudent().getId())) {
                Long addressId = studentAddress.getId();

                // Delete address if it exists
                addressService.deleteAddressById(addressId);
                studentService.deleteStudentById(id);
            } else {
                // If the ids don't match, just delete the student
                studentService.deleteStudentById(id);
            }
        } else {
            // If studentAddress or student is null, delete the student by id
            LOGGER.info("Address or student is null, only deleting student");
            studentService.deleteStudentById(id);
        }
        return "redirect:/students";
    }
}
