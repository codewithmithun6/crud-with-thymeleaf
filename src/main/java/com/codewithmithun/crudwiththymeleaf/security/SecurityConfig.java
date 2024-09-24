//package com.codewithmithun.crudwiththymeleaf.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/login","/", "/register").permitAll()  // Public pages
//                .requestMatchers("/admin/**").hasRole("ADMIN")  // Restrict /admin/** to users with "ADMIN" role
//                .anyRequest().authenticated()  // Require authentication for other requests
//                .and()
//                .formLogin()
//                .loginPage("/login")  // Custom login page
//                .defaultSuccessUrl("/students", true)  // Redirect to /students after login
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout")  // Redirect to login page after logout
//                .permitAll();
//
//        return http.build();
//    }
//
//    // Password encoder bean for encrypting passwords
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
