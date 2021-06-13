package com.example.task.controller;

import com.example.task.model.Order;
import com.example.task.service.OrderService;
import com.example.task.wrapper.ResponseListWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@ResponseBody
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "orders_without_details")
    private String ordersWithoutDetails(){
        List<Order> orderList = orderService.getAll();

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(2016, Calendar.SEPTEMBER, 6);
        Date date = gregorianCalendar.getTime();

        List<Order> resultList = orderList.stream()
                .filter(order -> order.getDate().before(date) && order.getDetail().isEmpty())
                .collect(Collectors.toList());
        ResponseListWrapper<Order> responseListWrapper = new ResponseListWrapper<Order>(resultList);

        return responseListWrapper.toString();
    }

}
