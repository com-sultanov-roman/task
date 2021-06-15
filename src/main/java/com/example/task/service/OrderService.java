package com.example.task.service;


import com.example.task.dto.OrderWithoutInvoicesDTO;
import com.example.task.model.Order;
import com.example.task.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll(){
        return orderRepository.getAll();
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public List<OrderWithoutInvoicesDTO> getOrdersWithoutInvoices(){return orderRepository.getOrdersWithoutInvoices();}

}
