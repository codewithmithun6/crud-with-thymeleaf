package com.codewithmithun.crudwiththymeleaf.controller;

import com.codewithmithun.crudwiththymeleaf.entities.Address;
import com.codewithmithun.crudwiththymeleaf.entities.Student;
import com.codewithmithun.crudwiththymeleaf.entities.Teacher;
import com.codewithmithun.crudwiththymeleaf.entities.TeacherAddress;
import com.codewithmithun.crudwiththymeleaf.service.AddressService;
import com.codewithmithun.crudwiththymeleaf.service.StudentService;
import com.codewithmithun.crudwiththymeleaf.service.TeacherAddressService;
import com.codewithmithun.crudwiththymeleaf.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherAddressController {

    @Autowired
    private TeacherAddressService teacherAddressService;

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/teachers_addresses/{teacherId}")
    public String saveOrUpdateTeacherAddress(@PathVariable("teacherId") Long teacherId, @ModelAttribute("address") TeacherAddress teacherAddress) {
        // Fetch the teacher by teacherId
        Teacher teacher = teacherService.getTeacherById(teacherId);

        if (teacher == null) {
            // Handle the case where the teacher doesn't exist
            throw new RuntimeException("Teacher not found with id: " + teacherId);
        }

        // Check if an address already exists for the teacher
        TeacherAddress existingTeacherAddress = teacherAddressService.getTeacherAddressByTeacherId(teacherId);

        if (existingTeacherAddress != null) {
            // If an teacher address already exists, update the existing address with the new data
            existingTeacherAddress.setState(teacherAddress.getState());
            existingTeacherAddress.setCity(teacherAddress.getCity());
            existingTeacherAddress.setStreet(teacherAddress.getStreet());
            existingTeacherAddress.setPinCode(teacherAddress.getPinCode());
            existingTeacherAddress.setCountry(teacherAddress.getCountry());
            existingTeacherAddress.setFullAddress(teacherAddress.getFullAddress());

            // Set the student (though it might already be set)
            existingTeacherAddress.setTeacher(teacher);

            // Save the updated address
            teacherAddressService.saveTeacherAddress(existingTeacherAddress);
        } else {
            // If no address exists, set the student and create a new address
            teacherAddress.setTeacher(teacher);

            // Save the new address
            teacherAddressService.saveTeacherAddress(teacherAddress);
        }

        // Redirect to the student details or list
        return "redirect:/teachers";
    }



    @GetMapping("/teachers_addresses/new/{teacherId}")
    public String createTeacherAddressForm(@PathVariable Long teacherId,Model model) {

        // Check if an address already exists for the student
        TeacherAddress existingTeacherAddress = teacherAddressService.getTeacherAddressByTeacherId(teacherId);
        if (existingTeacherAddress != null){

            model.addAttribute("address", teacherAddressService.getTeacherAddressById(existingTeacherAddress.getId()));
            model.addAttribute("tId", teacherId);

        }else {
            // create student object to hold student form data
            TeacherAddress TeacherAddress = new TeacherAddress();
            model.addAttribute("address", TeacherAddress);
            model.addAttribute("tId", teacherId);
            System.out.println("Teacher ID: "+teacherId);

        }
        return "edit_teacher_address";

    }

}
