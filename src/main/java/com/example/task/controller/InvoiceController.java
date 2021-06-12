package com.example.task.controller;

import com.example.task.model.Invoice;
import com.example.task.service.InvoiceService;
import com.example.task.wrapper.InvoiceListResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@ResponseBody
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping(value = "/expired_invoices")
    private String getExpiredInvoices() throws JsonProcessingException {
        List<Invoice> invoiceList = invoiceService.getAll().stream().
                filter(invoice -> invoice.getIssued().after(invoice.getDue()))
                .collect(Collectors.toList());

        InvoiceListResponseWrapper invoiceListResponseWrapper = new InvoiceListResponseWrapper(invoiceList);
        return invoiceListResponseWrapper.toString();
    }
}
