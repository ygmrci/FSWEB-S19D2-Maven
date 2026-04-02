package com.workintech.s18d4.service;

import com.workintech.s18d4.repository.CustomerRepository;
import com.workintech.s18d4.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer find(Long id) {
        Optional<Customer>  customer =customerRepository.findById(id);
        return customer.orElseThrow(()-> new RuntimeException("Customer bulunamadı : "+ id));
    }

    @Override
    public Customer save(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Customer delete(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customerRepository.delete(customer);
            return customer;
        }
        return null;
    }
}
