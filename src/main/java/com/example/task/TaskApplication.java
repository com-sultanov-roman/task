package com.example.task;

import com.example.task.model.*;
import com.example.task.repository.*;
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
    public static ProductRepository productRepository;
    public static CategoryRepository categoryRepository;


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

    private static void generateOrdersInvoicesCustomers() {
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
            name = String.valueOf((int) (Integer.MAX_VALUE * Math.random()));

            //customer.setName("Tyler");
            customer.setName(name);
            customer.setPhone("999-999-999");
            orders = orderList.subList(i - 5, i);
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

    private static void generateProductsCategories() {
        List<String> categoryNameList = new ArrayList<>();
        for (int i = 0; i < 50; i++) categoryNameList.add("name#" + String.valueOf(i) + "c");

        List<String> productNameList = new ArrayList<>();
        for (int i = 0; i < 500; i++) productNameList.add("name#" + String.valueOf(i) + "p");

        Category category;
        Product product;
        List<Product> products;
        List<String> productNameTmpList;

        for (int i = 0; i < 50; i++) {

            products = new ArrayList<>();
            productNameTmpList = productNameList.subList(i * 10, i * 10 + 10);
            for (String productName : productNameList) {
                Product product1 = new Product();
                product1.setName(productName);
                product1.setPrice(new BigDecimal("42.12"));
                product1.setDescription("product");
                product1.setPhoto("photo");
                products.add(product1);

            }

            Category category1 = new Category();
            category1.setName(categoryNameList.get(i));
            category1.setProducts(products);


            categoryRepository.save(category1);

            for(Product p: products){
                p.setCategory(category1);
                productRepository.save(p);
            }



        }


    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(TaskApplication.class, args);

        invoiceRepository = applicationContext.getBean(InvoiceRepository.class);
        orderRepository = applicationContext.getBean(OrderRepository.class);
        customerRepository = applicationContext.getBean(CustomerRepository.class);
        productRepository = applicationContext.getBean(ProductRepository.class);
        categoryRepository = applicationContext.getBean(CategoryRepository.class);

        //generateOrdersInvoicesCustomers();
        //generateProductsCategories();
    }

}
