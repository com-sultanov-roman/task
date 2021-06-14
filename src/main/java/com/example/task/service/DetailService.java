package com.example.task.service;

import com.example.task.model.Detail;
import com.example.task.repository.DetailRepository;
import org.springframework.stereotype.Service;

@Service
public class DetailService {

    private final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public Detail save(Detail detail){
        return detailRepository.save(detail);
    }
}
