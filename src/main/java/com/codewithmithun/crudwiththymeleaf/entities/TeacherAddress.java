package com.codewithmithun.crudwiththymeleaf.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "teachers_addresses")
public class TeacherAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "pin_code")
    private String pinCode;
    @Column(name = "country")
    private String country;
    @Column(name = "full_address")
    private String fullAddress;

    @OneToOne(cascade = CascadeType.ALL)  // Automatically manage address persistence
    @JoinColumn(name = "teacher_id")      // Foreign key in Student table
    private Teacher teacher;

}
