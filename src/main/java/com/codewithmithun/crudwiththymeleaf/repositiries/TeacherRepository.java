package com.codewithmithun.crudwiththymeleaf.repositiries;

import com.codewithmithun.crudwiththymeleaf.entities.Student;
import com.codewithmithun.crudwiththymeleaf.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Page<Teacher> findByFirstNameContainingOrLastNameContainingOrEmailContainingOrMobileNumberContaining(
            String firstName, String lastName, String email, String mobileNumber, Pageable pageable);
}
