package com.codewithmithun.crudwiththymeleaf.dataLoader;

import com.codewithmithun.crudwiththymeleaf.entities.User;
//import com.codewithmithun.crudwiththymeleaf.enumData.Role;
import com.codewithmithun.crudwiththymeleaf.repositiries.UserRepository;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {


    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    private final PasswordEncoder passwordEncoder;



    @Override
    public void run(String... args) throws Exception {
        // Create an admin user
        User adminUser = new User();
        adminUser.setUserName("admin");
//        adminUser.setPassword(passwordEncoder.encode("admin123"));
        adminUser.setPassword("admin123");
//        adminUser.setRole(Role.ROLE_ADMIN);
        userRepository.save(adminUser);

        // Create a regular user
        User regularUser = new User();
        regularUser.setUserName("user");
//        regularUser.setPassword(passwordEncoder.encode("user123"));
        regularUser.setPassword("user123");
//        regularUser.setRole(Role.ROLE_USER);
        userRepository.save(regularUser);
    }
}

