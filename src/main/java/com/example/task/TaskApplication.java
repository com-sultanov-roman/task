package com.example.task;

import com.example.task.model.Customer;
import com.example.task.model.Invoice;
import com.example.task.model.Order;
import com.example.task.repository.CustomerRepository;
import com.example.task.repository.InvoiceRepository;
import com.example.task.repository.OrderRepository;
import com.example.task.service.InvoiceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.*;

@SpringBootApplication
public class TaskApplication {

    public static InvoiceRepository invoiceRepository;
    public static OrderRepository orderRepository;
    public static CustomerRepository customerRepository;


    public static Invoice generateInvoice() {

        Order order = new Order();

        int i = (int) (Math.random());
        long date;

        Invoice invoice = new Invoice();
        invoice.setDue(new Date((long) (Math.random() * System.currentTimeMillis())));
        invoice.setIssued(new Date(invoice.getDue().getTime() + 100400000));
        invoice.setAmount(new BigDecimal((int) (Math.random() * 0xFFFF)));

        //GregorianCalendar gregorianCalendar = new GregorianCalendar(2016, Calendar.JUNE, 12);

        date = (i == 1) ? invoice.getIssued().getTime() - 100400000 : invoice.getIssued().getTime() + 100400000;

       //if(i == 1) order.setDate(gregorianCalendar.getTime());

        order.setInvoice(invoice);
        invoice.setOrder(order);
        order.setDate(new Date(date));
        return invoice;
    }

    private static void generateOrdersInvoicesCustomers(){
                List<Order> orderList = new ArrayList<>();
        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice = null;
        for (int i = 0; i < 250; ++i) {
            invoice = generateInvoice();
            orderList.add(invoice.getOrder());
            invoiceList.add(invoice);
        }

        Customer customer;
        List<Order> orders;
        for (int i = 5; i <= 250; i += 5) {
            customer = new Customer();
            customer.setAddress("paper street");
            customer.setCountry("LAX");

            String name;
            name = String.valueOf((int)(Integer.MAX_VALUE * Math.random()));

            //customer.setName("Tyler");
            customer.setName(name);
            customer.setPhone("999-999-999");
            orders = orderList.subList(i-5, i);
            for (Order order : orders) {
                order.setCustomer(customer);
            }
            customerRepository.save(customer);
        }

        for (int i = 0; i < 250; ++i) {
            orderRepository.save(invoiceList.get(i).getOrder());
            invoiceRepository.save(invoiceList.get(i));
        }
    }

    private static void generateProductsCategories(){

    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TaskApplication.class, args);
        InvoiceRepository invoiceRepository = applicationContext.getBean(InvoiceRepository.class);
        OrderRepository orderRepository = applicationContext.getBean(OrderRepository.class);
        CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);
        generateOrdersInvoicesCustomers();
    }

}
