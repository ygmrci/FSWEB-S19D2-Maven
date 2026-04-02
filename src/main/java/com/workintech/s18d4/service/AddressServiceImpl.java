package com.workintech.s18d4.service;

import com.workintech.s18d4.repository.AddressRepository;
import com.workintech.s18d4.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.orElseThrow(()-> new RuntimeException("Address bulunumadı : " + id));
    }

    @Override
    public Address save(Address address) {

        return addressRepository.save(address);
    }

    @Override
    public void delete(Long id) {
        Address address = findById(id);
        addressRepository.delete(address);

    }
}
