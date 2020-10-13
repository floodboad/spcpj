package com.ocean.controller;

import com.ocean.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ProductController {
    @Autowired
    ProductService productService;

    @Value("${version}")
    String version;

    @RequestMapping("/products")
    public Object products(){
        System.out.println(version + "----version");
        return productService.listProducts();
    }
}
