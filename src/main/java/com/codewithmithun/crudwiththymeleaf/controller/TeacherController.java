package com.codewithmithun.crudwiththymeleaf.controller;


import com.codewithmithun.crudwiththymeleaf.entities.Student;
import com.codewithmithun.crudwiththymeleaf.entities.Teacher;
import com.codewithmithun.crudwiththymeleaf.service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        super();
        this.teacherService = teacherService;
    }

    // handler method to handle list teachers and return mode and view
    @GetMapping("/teachers")
    public String listTeachers(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(defaultValue = "") String keyword){

        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage;

        // If search is not empty, search the teachers by name or email or mobile
        if (keyword != null && !keyword.isEmpty()) {
            teacherPage = teacherService.searchTeachers(keyword, pageable);
        } else {
            teacherPage = teacherService.getAllTeachers(pageable);
        }


        model.addAttribute("teachers", teacherPage.getContent());  // Current page data
        model.addAttribute("currentPage", page);                   // Current page number
        model.addAttribute("totalPages", teacherPage.getTotalPages()); // Total pages
        model.addAttribute("search", keyword);


        return "teachers";
    }

    @GetMapping("/teachers/new")
    public String createTeacherForm(Model model) {

        // create teachers object to hold teachers form data
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "create_teacher";

    }

    @PostMapping("/teachers")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/edit/{id}")
    public String editTeacherForm(@PathVariable Long id, Model model) {
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "edit_teacher";
    }

    @PostMapping("/teachers/{id}")
    public String updateTeacher(@PathVariable Long id,
                                @ModelAttribute("teacher") Teacher teacher,
                                Model model) {

        // get teacher from database by id
        Teacher existingTeacher = teacherService.getTeacherById(id);
        existingTeacher.setId(id);
        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setMobileNumber(teacher.getMobileNumber());
        existingTeacher.setGender(teacher.getGender());
        existingTeacher.setDob(teacher.getDob());
        existingTeacher.setSubject(teacher.getSubject());

        // save updated teacher object
        teacherService.updateTeacher(existingTeacher);
        return "redirect:/teachers";
    }

    // handler method to handle delete teacher request

    @GetMapping("/teachers/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/teachers";
    }


}
