package com.codewithmithun.crudwiththymeleaf.entities;


import com.codewithmithun.crudwiththymeleaf.enumData.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "department")
    private String department;

    @Column(name = "parents_mobile")
    private String parentsMobile;

//    @Column(name = "gender")
//    private String gender;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    // Date of Birth in DDMMYYYY format
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Date of birth must be in DD-MM-YYYY format")
    private String dob;

    @OneToOne(cascade = CascadeType.ALL)  // Automatically manage address persistence
    @JoinColumn(name = "address_id")      // Foreign key in Student table
    private Address address;

}
