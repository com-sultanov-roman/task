package com.example.task;

import com.example.task.model.Invoice;
import com.example.task.model.Order;
import com.example.task.repository.InvoiceRepository;
import com.example.task.repository.OrderRepository;
import com.example.task.service.InvoiceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication

public class TaskApplication {


    public static Invoice generateInvoice() {

        Order order = new Order();

        int i = (int) (Math.random());
        long date;

        Invoice invoice = new Invoice();
        invoice.setDue(new Date((long) (Math.random() * System.currentTimeMillis())));
        invoice.setIssued(new Date(invoice.getDue().getTime() + 100400000));
        invoice.setAmount(new BigDecimal((int) (Math.random() * 0xFFFF)));

        date = (i == 1) ? invoice.getIssued().getTime() - 100400000 : invoice.getIssued().getTime() + 100400000;

        order.setInvoice(invoice);
        invoice.setOrder(order);
        order.setDate(new Date(date));


        return invoice;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TaskApplication.class, args);


        InvoiceRepository invoiceRepository = applicationContext.getBean(InvoiceRepository.class);
        OrderRepository orderRepository = applicationContext.getBean(OrderRepository.class);

        Invoice invoice = null;
        for (int i = 0; i < 250; ++i){
			invoice = generateInvoice();
			orderRepository.save(invoice.getOrder());
			invoiceRepository.save(invoice);
		}


    }

}
