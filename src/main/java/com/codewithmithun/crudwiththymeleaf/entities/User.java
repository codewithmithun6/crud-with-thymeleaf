package com.codewithmithun.crudwiththymeleaf.entities;

//import com.codewithmithun.crudwiththymeleaf.enumData.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

//    @Enumerated(EnumType.STRING)
//    private Role role;  // Add this field to store user roles


}
