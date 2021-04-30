package com.ocean.test;

import feign.Client;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;

public class FeignProductServiceImpl implements FeignProductService{

    @Autowired
    FeignProductService feignProductService;
    @Override
    public int test() {
        Feign.builder().client((Client) feignProductService).build();
        return 0;
    }
}
