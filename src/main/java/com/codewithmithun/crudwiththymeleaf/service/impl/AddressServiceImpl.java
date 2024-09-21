package com.codewithmithun.crudwiththymeleaf.service.impl;

import com.codewithmithun.crudwiththymeleaf.entities.Address;
import com.codewithmithun.crudwiththymeleaf.repositiries.AddressRepository;
import com.codewithmithun.crudwiththymeleaf.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address getAddressByStudentId(Long studentId) {
        return addressRepository.getAddressByStudentId(studentId);
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).get();
    }
}
