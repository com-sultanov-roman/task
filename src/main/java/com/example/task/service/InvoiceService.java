package com.example.task.service;

import com.example.task.model.Invoice;
import com.example.task.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {


    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getOverdueInvoices(){
        return this.invoiceRepository.getOverdueInvoices();
    }

    public List<Invoice> getAll(){
        return invoiceRepository.getAll();
    }

    public Invoice save(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

}
