package com.example.task.repository;

import com.example.task.dto.BulkProductDTO;
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

    @Query(value = "SELECT name, price FROM(\n" +
            "SELECT pr_id AS id, AVG(quantity) AS avg_quantity FROM detail GROUP by pr_id\n" +
            ") X, product p WHERE avg_quantity >=8 AND X.id = p.id", nativeQuery = true)
    List<BulkProductDTO> getBulkProducts();
}
