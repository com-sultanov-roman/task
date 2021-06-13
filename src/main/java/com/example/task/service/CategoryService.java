package com.example.task.service;

import com.example.task.model.Category;
import com.example.task.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }

    public Category getProductCategoryById(int productId){
        return categoryRepository.getProductCategoryById(productId);
    }

}
