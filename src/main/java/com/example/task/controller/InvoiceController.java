package com.example.task.controller;

import com.example.task.dto.OverpaidInvoicesDTO;
import com.example.task.model.Invoice;
import com.example.task.service.InvoiceService;
import com.example.task.wrapper.ResponseListWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        ResponseListWrapper<Invoice> responseListWrapper = new ResponseListWrapper<Invoice>(invoiceList);
        return responseListWrapper.toString();
    }

    @GetMapping(value = "/wrong_date_invoices")
    private String wrongDateInvoices() throws JsonProcessingException {
        List<Invoice> invoiceList = invoiceService.getAll().stream()
                .filter(invoice -> invoice.getOrder()!=null)
                .filter(invoice -> invoice.getIssued().before(invoice.getOrder().getDate()))
                .collect(Collectors.toList());

        List<Map<String, Long>> resultList = new ArrayList<>();
        invoiceList.forEach(invoice -> {
            Map<String, Long> map = new HashMap<>();
            map.put("invoice_id", (long) invoice.getId());
            map.put("invoice_issued_date", invoice.getIssued().getTime());
            map.put("order_id", (long) invoice.getOrder().getId());
            map.put("order_placed_date", invoice.getOrder().getDate().getTime());
            resultList.add(map);
        });

        ResponseListWrapper<Map<String, Long>> responseListWrapper = new ResponseListWrapper<Map<String, Long>>(resultList);
        return responseListWrapper.toString();
    }

    @GetMapping(value = "/overpaid_invoices")
    private String getOverpaidInvoices(){
        List<OverpaidInvoicesDTO> overpaidInvoicesDTOList = invoiceService.getOverpaidInvoices();
        ResponseListWrapper<OverpaidInvoicesDTO> overpaidInvoicesDTOResponseListWrapper = new ResponseListWrapper<>(overpaidInvoicesDTOList);
        return overpaidInvoicesDTOResponseListWrapper.toString();
    }
}
