package com.example.task.service;

import com.example.task.dto.CustomersLastOrderDTO;
import com.example.task.dto.NumberOfProductsInYearDTO;
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

    public List<Customer> getCustomersWithoutOrders() {
        return customerRepository.getCustomersWithoutOrders();
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public Customer getCustomerById(int id) {
        return customerRepository.getCustomerById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<NumberOfProductsInYearDTO> getNumberOfProductsInYear() {
        return customerRepository.getNumberOfProductsInYear();
    }

    public List<CustomersLastOrderDTO> getCustomersLastOrder() {
        return customerRepository.getCustomersLastOrder();
    }

}
