package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping({"/address", "/workintech/address"})
    public List<Address> getAll(){
        return addressService.findAll();

    }
    @GetMapping({"/address/{id}", "/workintech/address/{id}"})
    public Address getAddressById(@PathVariable Long id){
        return addressService.findById(id);

    }
    @PostMapping({"/address", "/workintech/address"})
    public Address save(@RequestBody Address address){
        return addressService.save(address);

    }
    @PutMapping({"/address/{id}", "/workintech/address/{id}"})
    public Address update(@PathVariable Long id, @RequestBody Address address){
        address.setId(id);
        return addressService.save(address);


    }
    @DeleteMapping({"/address/{id}", "/workintech/address/{id}"})
    public void delete(@PathVariable Long id){
        addressService.delete(id);

    }


}
