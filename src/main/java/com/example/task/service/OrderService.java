package com.example.task.service;


import com.example.task.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final InvoiceRepository invoiceRepository;

    public OrderService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }
}
