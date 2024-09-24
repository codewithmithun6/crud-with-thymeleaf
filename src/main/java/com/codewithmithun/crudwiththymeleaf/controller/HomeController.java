package com.codewithmithun.crudwiththymeleaf.controller;

import com.codewithmithun.crudwiththymeleaf.entities.User;
import com.codewithmithun.crudwiththymeleaf.repositiries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index(){
        return "index";
    }

    // show login page
    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(String username, String password) {
        // Add your authentication logic here
        if (isValidUser(username, password)) {
            return "redirect:/students";  // Redirect to the dashboard upon successful login
        } else {
            return "redirect:/login?error";  // Redirect back to login with an error parameter
        }
    }

    // Placeholder authentication method
    private boolean isValidUser(String username, String password) {
        // Your user authentication logic here (e.g., check against a database)
        User user = userRepository.findByUserName(username);

        // Check if adminUser is null before accessing its properties to avoid NullPointerException
        if (user == null) {
            return false;  // Return false if no user is found
        }
        return user.getUserName().equalsIgnoreCase(username) && user.getPassword().equals(password);

    }

//    @GetMapping("/admin/students")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String adminDashboard() {
//        return "admin/students";  // This page is only accessible to users with the "ADMIN" role
//    }
//
//
//    @GetMapping("/user/students")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public String studentsDashboard() {
//        return "students";  // This page is accessible to both "USER" and "ADMIN"
//    }


}


