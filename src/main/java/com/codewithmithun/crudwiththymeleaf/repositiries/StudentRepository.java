package com.codewithmithun.crudwiththymeleaf.repositiries;

import com.codewithmithun.crudwiththymeleaf.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

    // Search by first name, last name, or email or parentsMobile
    Page<Student> findByFirstNameContainingOrLastNameContainingOrEmailContainingOrParentsMobileContaining(
            String firstName, String lastName, String email, String parentsMobile, Pageable pageable);
}
