package com.example.task.controller;

import com.example.task.dto.CustomersLastOrderDTO;
import com.example.task.dto.NumberOfProductsInYearDTO;
import com.example.task.model.Customer;
import com.example.task.model.Order;
import com.example.task.service.CustomerService;
import com.example.task.wrapper.ResponseListWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@ResponseBody
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
        List<Customer> customerList = customerService.getAll();
        List<CustomersLastOrderDTO> resultList = new ArrayList<>();
        customerList.forEach(customer -> {
            if (!customer.getOrders().isEmpty()) {
                CustomersLastOrderDTO customersLastOrderDTO = new CustomersLastOrderDTO();
                Order mostRecentOrder;
                customersLastOrderDTO.setId(customer.getId());
                customersLastOrderDTO.setName(customer.getName());
                mostRecentOrder = Collections.max(customer.getOrders(), (o1, o2) -> Long.signum(o1.getDate().getTime() - o2.getDate().getTime()));
                customersLastOrderDTO.setDate(mostRecentOrder.getDate());
                resultList.add(customersLastOrderDTO);
            }
        });
        ResponseListWrapper<CustomersLastOrderDTO> responseListWrapper = new ResponseListWrapper<>(resultList);
        return responseListWrapper.toString();
    }

    @GetMapping(value = "number_of_products_in_year")
    private String getNumberOfProductsInYear() {
        List<NumberOfProductsInYearDTO> numberOfProductsInYearDTOList = customerService.getNumberOfProductsInYear();
        ResponseListWrapper<NumberOfProductsInYearDTO> responseListWrapper = new ResponseListWrapper<>(numberOfProductsInYearDTOList);
        return responseListWrapper.toString();
    }

}
