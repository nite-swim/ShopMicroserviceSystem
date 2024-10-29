package com.itcast.controller;

import com.google.gson.Gson;
import com.itcast.pojo.Product;
import com.itcast.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private Gson gson;

    @GetMapping("/{pid}")
    public Product getProduct(@PathVariable("pid") int pid) {
        Product product = productService.findById(1);
        log.info("查询到商品：" + gson.toJson(product));
        return product;
    }
}
