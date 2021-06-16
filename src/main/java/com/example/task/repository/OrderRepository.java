package com.example.task.repository;

import com.example.task.dto.OrderWithoutInvoicesDTO;
import com.example.task.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query(value = "SELECT i FROM Order i")
    public List<Order> getAll();

    @Query(value = "SELECT id, date, SUM(total) as totalPrice FROM(SELECT o.id, o.date, d.quantity * p.price as total FROM product p, orders o, detail d WHERE d.ord_id = o.id AND d.pr_id = p.id AND o.id NOT IN (SELECT ord_id FROM invoice)) p GROUP BY id, date;", nativeQuery = true)
    public List<OrderWithoutInvoicesDTO> getOrdersWithoutInvoices();


}
