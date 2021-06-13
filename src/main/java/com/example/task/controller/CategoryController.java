package com.example.task.controller;

import com.example.task.model.Category;
import com.example.task.service.CategoryService;
import com.example.task.wrapper.ResponseListWrapper;
import com.example.task.wrapper.ResponseObjectWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping(value = "category/list")
    @ResponseBody
    private String getCategoryList(){
        List<Category> categoryList = categoryService.getAll();
        ResponseListWrapper<Category> responseListWrapper = new ResponseListWrapper<>(categoryList);
        return responseListWrapper.toString();
    }

    @GetMapping(value = "category")
    @ResponseBody
    private String getCategoryById(@RequestParam(name = "product_id") int productId, HttpServletResponse httpServletResponse){
        Category category = categoryService.getProductCategoryById(productId);
        ResponseObjectWrapper<Category> responseObjectWrapper = new ResponseObjectWrapper<>();
        if(category != null){
            responseObjectWrapper.setResult(category);
        }else{
            httpServletResponse.setStatus(404);
        }
        return responseObjectWrapper.toString();
    }

}
