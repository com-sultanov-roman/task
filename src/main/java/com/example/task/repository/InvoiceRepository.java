package com.example.task.repository;
import com.example.task.dto.OverpaidInvoicesDTO;
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

//    @Query(value = "SELECT * FROM (SELECT i.id as invoice_id, SUM(p.amount) - i.amount as amount_to_be_reimbursed  FROM invoice i JOIN payment p ON i.id = p.inv_id GROUP BY p.amount, i.id) X WHERE amount_to_be_reimbursed > 0", nativeQuery = true)
//    List<OverpaidInvoicesDTO> getOverpaidInvoices();

    @Query(value = "SELECT * FROM(\n" +
            "SELECT invoice_id, SUM(payment_amount) - invoice_amount  as amount_to_be_reimbursed\n" +
            "FROM(SELECT i.id as invoice_id, i.amount as invoice_amount, p.id as payment_id, p.amount as payment_amount \n" +
            "FROM invoice i, payment p WHERE i.id = p.inv_id ORDER BY i.id) X GROUP BY invoice_id, invoice_amount ORDER BY invoice_id\n" +
            ") Y WHERE amount_to_be_reimbursed > 0;", nativeQuery = true)
    List<OverpaidInvoicesDTO> getOverpaidInvoices();


}
