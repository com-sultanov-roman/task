package com.example.task.wrapper;

import com.example.task.model.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceListResponseWrapper {
    private List<Invoice> result;

    @SneakyThrows
    @Override
    public String toString(){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writer().writeValueAsString(this);
    }
}
