package com.codewithmithun.crudwiththymeleaf.service.impl;

import com.codewithmithun.crudwiththymeleaf.entities.Address;
import com.codewithmithun.crudwiththymeleaf.entities.TeacherAddress;
import com.codewithmithun.crudwiththymeleaf.repositiries.AddressRepository;
import com.codewithmithun.crudwiththymeleaf.repositiries.TeacherAddressRepository;
import com.codewithmithun.crudwiththymeleaf.service.AddressService;
import com.codewithmithun.crudwiththymeleaf.service.TeacherAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherAddressServiceImpl implements TeacherAddressService {
    @Autowired
    private TeacherAddressRepository teacherAddressRepository;
    @Override
    public TeacherAddress updateTeacherAddress(TeacherAddress teacherAddress) {
        return teacherAddressRepository.save(teacherAddress);
    }

    @Override
    public TeacherAddress saveTeacherAddress(TeacherAddress teacherAddress) {
        return teacherAddressRepository.save(teacherAddress);
    }

    @Override
    public TeacherAddress getTeacherAddressByTeacherId(Long teacherId) {
        return teacherAddressRepository.getTeacherAddressByTeacherId(teacherId);
    }

    @Override
    public TeacherAddress getTeacherAddressById(Long id) {
        return teacherAddressRepository.findById(id).get();
    }

    @Override
    public void deleteTeacherAddressById(Long addressId) {
        teacherAddressRepository.deleteById(addressId);

    }
}
