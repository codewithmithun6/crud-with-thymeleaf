package com.codewithmithun.crudwiththymeleaf.service;

import com.codewithmithun.crudwiththymeleaf.entities.Address;
import com.codewithmithun.crudwiththymeleaf.entities.TeacherAddress;

public interface TeacherAddressService {

    TeacherAddress updateTeacherAddress(TeacherAddress teacherAddress);
    TeacherAddress saveTeacherAddress(TeacherAddress teacherAddress);

    TeacherAddress getTeacherAddressByTeacherId(Long teacherId);

    TeacherAddress getTeacherAddressById(Long id);

    void deleteTeacherAddressById(Long addressId);
}
