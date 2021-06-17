package com.example.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


public interface CustomersLastOrderDTO {

    public int getId();

    public String getName();

    public Date getLast_order_date();

}
