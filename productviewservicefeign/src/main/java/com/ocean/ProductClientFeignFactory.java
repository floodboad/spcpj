package com.ocean;

import feign.hystrix.FallbackFactory;

public class ProductClientFeignFactory implements FallbackFactory<ProductClientFeign> {

    private final ProductClientFeignHystrix productClientFeignHystrix;

    public ProductClientFeignFactory(ProductClientFeignHystrix productClientFeignHystrix){
        this.productClientFeignHystrix = productClientFeignHystrix;
    }

    @Override
    public ProductClientFeign create(Throwable throwable) {
        throwable.printStackTrace();
        return productClientFeignHystrix;
    }
}
