package com.itcast.service;

//import com.itcast.service.fallback.ProductServiceFallback;
import com.itcast.pojo.Product;
import com.itcast.service.fallback.ProductServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product", fallback = ProductServiceFallback.class)
public interface ProductService {
    //指定调用提供者的哪个方法
    //@FeignClient+@GetMapping就是一个完整的请求路径http://service-product/product/{pid}
    @GetMapping(value = "/product/{pid}")
//    @RequestMapping(value = "/product/{pid}")
    Product findById(@PathVariable("pid") Integer pid);
}
