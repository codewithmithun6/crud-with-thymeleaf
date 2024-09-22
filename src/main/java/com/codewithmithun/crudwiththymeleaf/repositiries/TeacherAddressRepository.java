package com.codewithmithun.crudwiththymeleaf.repositiries;

import com.codewithmithun.crudwiththymeleaf.entities.Address;
import com.codewithmithun.crudwiththymeleaf.entities.TeacherAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherAddressRepository extends JpaRepository<TeacherAddress,Long> {
    TeacherAddress getTeacherAddressByTeacherId(Long teacherId);
}
