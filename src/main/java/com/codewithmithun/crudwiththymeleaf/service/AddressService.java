package com.codewithmithun.crudwiththymeleaf.service;

import com.codewithmithun.crudwiththymeleaf.entities.Address;

public interface AddressService {

    Address updateAddress(Address address);

    Address getAddressById(Long id);
}
