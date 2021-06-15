package com.example.task.controller;

import com.example.task.dto.MakePaymentHttpRequestDTO;
import com.example.task.dto.MakePaymentHttpResponse;
import com.example.task.model.Invoice;
import com.example.task.model.Payment;
import com.example.task.repository.PaymentRepository;
import com.example.task.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Controller
@ResponseBody
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
    private MakePaymentHttpResponse makePayment(@RequestBody MakePaymentHttpRequestDTO makePaymentHttpRequestDTO, HttpServletRequest httpServletRequest) {
        Invoice invoice = invoiceService.getInvoiceById(makePaymentHttpRequestDTO.getInvoice_id());
        MakePaymentHttpResponse makePaymentHttpResponse = new MakePaymentHttpResponse();
        if(invoice != null){
            Payment payment = new Payment();
            payment.setInvoice(invoice);
            payment.setTimestamp(new Date(System.currentTimeMillis()));
            payment.setAmount(invoice.getAmount());
            makePaymentHttpResponse.setStatus("SUCCESS");
            makePaymentHttpResponse.setPayment(payment);

            invoiceService.save(invoice);
            paymentService.save(payment);

        }else{
            makePaymentHttpResponse.setStatus("FAILED");
        }
        return makePaymentHttpResponse;
    }


}
