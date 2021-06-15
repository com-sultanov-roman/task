package com.example.task.repository;
import com.example.task.model.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Integer>{

    @Query(value = "SELECT i FROM Invoice i WHERE i.issued > i.due")
    List<Invoice> getOverdueInvoices();

    @Query(value = "SELECT i FROM Invoice i")
    List<Invoice> getAll();

    Invoice getInvoiceById(int id);


}
