package com.example.task.wrapper;

import com.example.task.model.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapper<T> {
    private List<T> result;

    @SneakyThrows
    @Override
    public String toString(){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writer().writeValueAsString(this);
    }
}
