package com.example.task.service;

import com.example.task.model.Customer;
import com.example.task.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomersWithoutOrders(){
        return customerRepository.getCustomersWithoutOrders();
    }

    public List<Customer> getAll(){
        return customerRepository.getAll();
    }
}
