package com.codewithmithun.crudwiththymeleaf.repositiries;

import com.codewithmithun.crudwiththymeleaf.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
