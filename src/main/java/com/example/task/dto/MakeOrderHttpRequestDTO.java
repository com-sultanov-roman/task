package com.example.task.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MakeOrderHttpRequestDTO {
    private int product_id;
    private int customer_id;
    private short quantity;
}
