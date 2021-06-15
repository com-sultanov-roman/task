package com.example.task.dto;


import java.math.BigDecimal;
import java.util.Date;

public interface OrderWithoutInvoicesDTO {
    int getId();

    Date getDate();

    Double getTotalPrice();
}
