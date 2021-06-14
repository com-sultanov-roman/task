package com.example.task.service;

import com.example.task.model.Detail;
import com.example.task.repository.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {

    private final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public List<Detail> getDetailByOrderId(int id){
        return detailRepository.getDetailsByOrderId(id);
    }

    public Detail save(Detail detail){
        return detailRepository.save(detail);
    }
}
