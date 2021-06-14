package com.example.task.service;

import com.example.task.model.Customer;
import com.example.task.model.Product;
import com.example.task.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Product getProductById(int id){
        return productRepository.getProductById(id);
    }

}
