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

    @Query(value = "SELECT id, date, SUM(total) as totalPrice FROM(\n" +
            "\tSELECT o.id, o.date, d.quantity * p.price as total\n" +
            "\tFROM product p, orders o, detail d\n" +
            "\tWHERE \n" +
            "\td.ord_id = o.id \n" +
            "\tAND \n" +
            "\td.pr_id = p.id\n" +
            "\tAND\n" +
            "\to.id NOT IN (SELECT ord_id FROM invoice)\n" +
            ") p GROUP BY id, date;", nativeQuery = true)
    public List<OrderWithoutInvoicesDTO> getOrdersWithoutInvoices();


}
