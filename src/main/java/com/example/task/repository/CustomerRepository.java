package com.example.task.repository;

import com.example.task.dto.CustomersLastOrderDTO;
import com.example.task.model.Customer;
import com.example.task.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
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

}
