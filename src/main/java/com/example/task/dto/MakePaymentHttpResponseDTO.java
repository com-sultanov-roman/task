package com.example.task.dto;

import com.example.task.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MakePaymentHttpResponseDTO {
    private String status;
    private Payment payment;
}
