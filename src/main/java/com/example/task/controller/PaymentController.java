package com.example.task.controller;

import com.example.task.dto.MakePaymentHttpRequestDTO;
import com.example.task.dto.MakePaymentHttpResponseDTO;
import com.example.task.model.Invoice;
import com.example.task.model.Payment;
import com.example.task.service.*;
import com.example.task.wrapper.ResponseObjectWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    private final OrderService orderService;

    private final InvoiceService invoiceService;

    private final CustomerService customerService;

    private final ProductService productService;

    private final DetailService detailService;

    public PaymentController(PaymentService paymentService, OrderService orderService, InvoiceService invoiceService, CustomerService customerService, ProductService productService, DetailService detailService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
        this.invoiceService = invoiceService;
        this.customerService = customerService;
        this.productService = productService;
        this.detailService = detailService;
    }


    @PostMapping(value = "payment", consumes = "application/json",  produces = "application/json")
    private MakePaymentHttpResponseDTO makePayment(@RequestBody MakePaymentHttpRequestDTO makePaymentHttpRequestDTO, HttpServletRequest httpServletRequest) {
        Invoice invoice = invoiceService.getInvoiceById(makePaymentHttpRequestDTO.getInvoice_id());
        MakePaymentHttpResponseDTO makePaymentHttpResponseDTO = new MakePaymentHttpResponseDTO();
        if(invoice != null){
            Payment payment = new Payment();
            payment.setInvoice(invoice);
            payment.setTimestamp(new Date(System.currentTimeMillis()));
            payment.setAmount(invoice.getAmount());
            makePaymentHttpResponseDTO.setStatus("SUCCESS");
            makePaymentHttpResponseDTO.setPayment(payment);

            invoiceService.save(invoice);
            paymentService.save(payment);

        }else{
            makePaymentHttpResponseDTO.setStatus("FAILED");
        }
        return makePaymentHttpResponseDTO;
    }

    @GetMapping(value = "payment/details")
    private String getPaymentDetailsById(@RequestParam(name="id") int id){
        Payment payment = paymentService.getPaymentById(id);
        ResponseObjectWrapper<Payment> responseObjectWrapper = new ResponseObjectWrapper<>(payment);
        return responseObjectWrapper.toString();
    }


}
