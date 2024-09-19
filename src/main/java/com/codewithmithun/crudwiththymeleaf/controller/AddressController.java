package com.codewithmithun.crudwiththymeleaf.controller;

import com.codewithmithun.crudwiththymeleaf.entities.Address;
import com.codewithmithun.crudwiththymeleaf.entities.Student;
import com.codewithmithun.crudwiththymeleaf.service.AddressService;
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

    @PostMapping("/addresses/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("address") Address address,
                                Model model) {

        // get student from database by id
        Address existingAddress = addressService.getAddressById(id);
        existingAddress.setId(id);
        existingAddress.setState(address.getState());
        existingAddress.setCity(address.getCity());
        existingAddress.setStreet(address.getStreet());
        existingAddress.setPinCode(address.getPinCode());
        existingAddress.setCountry(address.getCountry());
        existingAddress.setFullAddress(address.getFullAddress());


        // save updated student object
        addressService.updateAddress(existingAddress);
        return "redirect:/students";
    }

    @GetMapping("/addresses/edit/{id}")
    public String viewAddressForm(@PathVariable Long id, Model model) {
        System.out.println("address: "+addressService.getAddressById(id));
        model.addAttribute("address", addressService.getAddressById(id));
        return "edit_address";
    }



//    @GetMapping("/addresses")
//   public String viewAddressForm(){
//        return "redirect:/view_address";
//
//   }

}
