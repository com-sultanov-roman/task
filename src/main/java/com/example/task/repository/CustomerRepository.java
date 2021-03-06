package com.example.task.repository;

import com.example.task.dto.CustomersLastOrderDTO;
import com.example.task.dto.NumberOfProductsInYearDTO;
import com.example.task.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM customer WHERE customer.id NOT IN (SELECT customer.id FROM customer, orders WHERE customer.id = orders.cust_id and (Orders.date BETWEEN '2016-01-01' and '2017-01-01' ))", nativeQuery = true)
    public List<Customer> getCustomersWithoutOrders();

    @Query(value = "SELECT * FROM customer", nativeQuery = true)
    public List<Customer> getAll();

    @Query(value = "SELECT customer.id, customer.name, MAX(date) AS last_order_date  FROM customer, orders WHERE customer.id = orders.cust_id GROUP BY customer.id ORDER BY id", nativeQuery = true)
    public List<CustomersLastOrderDTO> getCustomersLastOrder();

    public Customer getCustomerById(int id);

    @Query(value = "SELECT customer.country as countryName, COUNT(customer.country) AS number FROM customer, orders WHERE customer.id = orders.cust_id AND orders.date BETWEEN '2016-01-01' AND '2017-01-01' GROUP BY customer.country", nativeQuery = true)
    public List<NumberOfProductsInYearDTO> getNumberOfProductsInYear();

}
