package com.itcast.service.fallback;

import com.itcast.pojo.Product;
import com.itcast.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductServiceFallback implements ProductService {
    @Override
    public Product findById(Integer pid) {
        Product product = new Product();
        product.setPid(-1);
        return product;
    }
}
