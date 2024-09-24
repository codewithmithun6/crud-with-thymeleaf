//package com.codewithmithun.crudwiththymeleaf.service;
//
//import com.codewithmithun.crudwiththymeleaf.entities.User;
//import com.codewithmithun.crudwiththymeleaf.repositiries.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        // Convert user's role into a SimpleGrantedAuthority
//        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().name());
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUserName())
//                .password(user.getPassword())
//                .authorities(Collections.singletonList(authority))  // Add the user's role as an authority
//                .build();
//    }
//}
//
//
