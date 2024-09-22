package com.codewithmithun.crudwiththymeleaf.service;

import com.codewithmithun.crudwiththymeleaf.entities.Address;

public interface AddressService {

    Address updateAddress(Address address);
    Address saveAddress(Address address);

    Address getAddressByStudentId(Long studentId);

    Address getAddressById(Long id);

    void deleteAddressById(Long addressId);
}
