package com.example.task.repository;

import com.example.task.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query(value = "SELECT * FROM category",nativeQuery = true)
    public List<Category> getAll();

    @Query(value = "SELECT c FROM Category c, Product p WHERE p.id = :product_id AND c = p.category")
    public Category getProductCategoryById(@Param("product_id") int productId);
}
