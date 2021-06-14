package com.example.task.repository;

import com.example.task.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> getAll();

    Product getProductById(int id);
}
