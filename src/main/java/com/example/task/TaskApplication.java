package com.example.task;

import com.example.task.model.Invoice;
import com.example.task.repository.InvoiceRepository;
import com.example.task.service.InvoiceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication

public class TaskApplication {

	public static Invoice generateInvoice(){
		Invoice invoice = new Invoice();
		invoice.setDue(new Date((long) (Math.random() * System.currentTimeMillis())));
		invoice.setIssued(new Date(invoice.getDue().getTime()+100400000));
		invoice.setAmount(new BigDecimal((int)(Math.random() * 0xFFFF)));
		return invoice;
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(TaskApplication.class, args);


		InvoiceRepository invoiceRepository = applicationContext.getBean(InvoiceRepository.class);
		for(int i = 0; i< 1000; ++i)
			invoiceRepository.save(generateInvoice());


	}

}
