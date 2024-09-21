package com.codewithmithun.crudwiththymeleaf.controller;

import com.codewithmithun.crudwiththymeleaf.entities.Address;
import com.codewithmithun.crudwiththymeleaf.entities.Student;
import com.codewithmithun.crudwiththymeleaf.service.AddressService;
import com.codewithmithun.crudwiththymeleaf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private StudentService studentService;

    @PostMapping("/addresses/{studentId}")
    public String saveOrUpdateAddress(@PathVariable("studentId") Long studentId, @ModelAttribute("address") Address address) {
        // Fetch the student by studentId
        Student student = studentService.getStudentById(studentId);

        if (student == null) {
            // Handle the case where the student doesn't exist
            throw new RuntimeException("Student not found with id: " + studentId);
        }

        // Check if an address already exists for the student
        Address existingAddress = addressService.getAddressByStudentId(studentId);

        if (existingAddress != null) {
            // If an address already exists, update the existing address with the new data
            existingAddress.setState(address.getState());
            existingAddress.setCity(address.getCity());
            existingAddress.setStreet(address.getStreet());
            existingAddress.setPinCode(address.getPinCode());
            existingAddress.setCountry(address.getCountry());
            existingAddress.setFullAddress(address.getFullAddress());

            // Set the student (though it might already be set)
            existingAddress.setStudent(student);

            // Save the updated address
            addressService.saveAddress(existingAddress);
        } else {
            // If no address exists, set the student and create a new address
            address.setStudent(student);

            // Save the new address
            addressService.saveAddress(address);
        }

        // Redirect to the student details or list
        return "redirect:/students";
    }



    @GetMapping("/addresses/new/{studentId}")
    public String createAddressForm(@PathVariable Long studentId,Model model) {

        // Check if an address already exists for the student
        Address existingAddress = addressService.getAddressByStudentId(studentId);
        if (existingAddress != null){

            model.addAttribute("address", addressService.getAddressById(existingAddress.getId()));
            model.addAttribute("sId", studentId);

        }else {
            // create student object to hold student form data
            Address address = new Address();
            model.addAttribute("address", address);
            model.addAttribute("sId", studentId);
            System.out.println("Student ID: "+studentId);

        }
        return "edit_address";

    }

}
