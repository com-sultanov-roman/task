package com.example.task.service;

import com.example.task.model.Payment;
import com.example.task.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }
}
