package com.example.task.controller;

import com.example.task.model.Category;
import com.example.task.model.Detail;
import com.example.task.model.Product;
import com.example.task.service.ProductService;
import com.example.task.wrapper.ResponseListWrapper;
import com.example.task.wrapper.ResponseObjectWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "product/list")
    @ResponseBody
    private String getCategoryList(){
        List<Product> productList = productService.getAll();
        ResponseListWrapper<Product> responseListWrapper = new ResponseListWrapper<>(productList);
        return responseListWrapper.toString();
    }

    @GetMapping(value = "product/details")
    @ResponseBody
    private String getProductDetailsById(@RequestParam(name = "product_id") int productId, HttpServletResponse httpServletResponse){
        Product product = productService.getProductById(productId);
        ResponseObjectWrapper<List<Detail>> responseObjectWrapper = new ResponseObjectWrapper<>();
        if(product != null){
            responseObjectWrapper.setResult(product.getDetails());
        }else{
            httpServletResponse.setStatus(404);
        }
        return responseObjectWrapper.toString();
    }


}
