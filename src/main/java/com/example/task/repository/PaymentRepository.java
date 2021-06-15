package com.example.task.repository;

import com.example.task.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    public Payment getPaymentById(int id);
}
