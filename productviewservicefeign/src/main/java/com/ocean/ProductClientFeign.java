package com.ocean;

import com.ocean.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "data-server",fallback = ProductClientFeignFactory.class)
public interface ProductClientFeign {
    @GetMapping("/products")
    public List<Product> listProducts();
}
