package com.example.task.controller;

import com.example.task.dto.MakeOrderHttpRequestDTO;
import com.example.task.dto.MakeOrderHttpResponseDTO;
import com.example.task.model.*;
import com.example.task.service.*;
import com.example.task.wrapper.ResponseListWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    private final OrderService orderService;

    private final InvoiceService invoiceService;

    private final CustomerService customerService;

    private final ProductService productService;

    private final DetailService detailService;


    public OrderController(OrderService orderService, InvoiceService invoiceService, CustomerService customerService, ProductService productService, DetailService detailService) {
        this.orderService = orderService;
        this.invoiceService = invoiceService;
        this.customerService = customerService;
        this.productService = productService;
        this.detailService = detailService;
    }

    @GetMapping(value = "orders_without_details")
    @ResponseBody
    private String ordersWithoutDetails() {
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

    @PostMapping(value = "order", consumes = "application/json", produces = "application/json")
    @ResponseBody
    private MakeOrderHttpResponseDTO makeOrder(@RequestBody MakeOrderHttpRequestDTO httpRequest, HttpServletResponse httpServletResponse) {

        int customerId = httpRequest.getCustomer_id();
        int productId = httpRequest.getProduct_id();
        short quantity = httpRequest.getQuantity();

        Customer customer = customerService.getCustomerById(customerId);
        Product product = productService.getProductById(productId);

        if (customer != null && product != null) {

            Order order = new Order();
            order.setDate(new Date(System.currentTimeMillis()));
            order.setCustomer(customer);

            Detail detail = new Detail();
            detail.setProduct(product);
            detail.setOrder(order);
            detail.setQuantity(quantity);

            customer.getOrders().add(order);

            Invoice invoice = new Invoice();
            invoice.setOrder(order);
            long curTime = System.currentTimeMillis();
            invoice.setIssued(new Date(curTime));
            invoice.setDue(new Date((curTime + 86_400_000 * 7)));
            invoice.setAmount(product.getPrice());


            orderService.save(order);
            detailService.save(detail);
            customerService.save(customer);
            invoice = invoiceService.save(invoice);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setHeader("status", "SUCCESS");
            httpServletResponse.setIntHeader("invoice_number", invoice.getId());
            return new MakeOrderHttpResponseDTO("SUCCESS", invoice.getId());
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpServletResponse.setHeader("status", "FAILED");
            return new MakeOrderHttpResponseDTO("FAILED", -1);
        }
    }

    @GetMapping(value = "order/details")
    @ResponseBody
    private String getOrderDetailsById(@RequestParam(name = "order_id") int orderId) {
        List<Detail> detailList = detailService.getDetailByOrderId(orderId);
        ResponseListWrapper<Detail> responseListWrapper = new ResponseListWrapper<>(detailList);
        return responseListWrapper.toString();
    }
}
