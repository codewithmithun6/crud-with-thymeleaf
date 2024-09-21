package com.codewithmithun.crudwiththymeleaf.repositiries;

import com.codewithmithun.crudwiththymeleaf.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Address getAddressByStudentId(Long studentId);
}
