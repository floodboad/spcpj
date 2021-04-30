package com.ocean.test;

import cc.crrc.core.context.feign.hystrix.FallbackableFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description 文件管理微服务
 * @FileName FeignFileService
 * @Author yangjingsheng
 * @Date 2021/03/16
 * @Version 1.0
 **/
@FeignClient(name = "data-server")
public interface FeignProductService extends FallbackableFeignClient {

    /**
     * 根据id删除file表
     * @return
     */
    @GetMapping(value = "/test")
    int test();
}
