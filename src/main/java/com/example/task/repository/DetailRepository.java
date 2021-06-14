package com.example.task.repository;

import com.example.task.model.Detail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Integer> {
    @Query(value = "SELECT d FROM Detail d, Order o WHERE o = d.order AND o.id = :id")
    public List<Detail> getDetailsByOrderId(int id);
}
