package com.example.task.controller;

import com.example.task.dto.CustomersLastOrderDTO;
import com.example.task.dto.NumberOfProductsInYearDTO;
import com.example.task.model.Customer;
import com.example.task.service.CustomerService;
import com.example.task.wrapper.ResponseListWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "customers_without_orders")
    private String getCustomersWithoutOrders() {
        List<Customer> customerList = customerService.getCustomersWithoutOrders();
        ResponseListWrapper<Customer> responseListWrapper = new ResponseListWrapper<>(customerList);
        return responseListWrapper.toString();
    }

    @GetMapping(value = "customers_last_orders")
    private String getCustomersLastOrders() {
        List<CustomersLastOrderDTO> customersLastOrderDTOList = customerService.getCustomersLastOrder();
        ResponseListWrapper<CustomersLastOrderDTO> responseListWrapper = new ResponseListWrapper<>(customersLastOrderDTOList);
        return responseListWrapper.toString();
    }

    @GetMapping(value = "number_of_products_in_year")
    private String getNumberOfProductsInYear() {
        List<NumberOfProductsInYearDTO> numberOfProductsInYearDTOList = customerService.getNumberOfProductsInYear();
        ResponseListWrapper<NumberOfProductsInYearDTO> responseListWrapper = new ResponseListWrapper<>(numberOfProductsInYearDTOList);
        return responseListWrapper.toString();
    }
}
