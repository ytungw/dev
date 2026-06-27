package com.ytung.product.controller;

import com.ytung.bean.product.Product;
import com.ytung.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.awt.color.ProfileDataException;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/product/{productId}")
    public Product getProductById(@PathVariable("productId") long productId){
        return productService.getProductById(productId);
    }
}
