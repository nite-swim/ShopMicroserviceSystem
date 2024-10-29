package com.itcast.controller;

import com.itcast.pojo.Order;
import com.itcast.pojo.Product;
import com.itcast.service.OrderService;
import com.itcast.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    //DiscoveryClient是专门负责服务注册和发现的，可以通过它获取到注册到服务中心的所有服务
    @Qualifier("com.itcast.service.ProductService")
    @Autowired
    private ProductService productService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //测试买一件商品
    //下单--feign
    @GetMapping("/order/prod/{pid}")
    public Order saveOrder(@PathVariable("pid") Integer pid) {
        log.info("接收到{}商品的下单请求，接下来调用商品微服务查询此商品信息", pid);
        //从nacos中获取服务地址
//        ServiceInstance serviceInstance = discoveryClient.getInstances("service-product").get(0);
//        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
//        log.info("从nacos中获取到的微服务地址为：" + url);

        //通过restTemplate调用商品微服务
//        Product product = restTemplate.getForObject("http://" + url + "/product/" + pid, Product.class);

        //通过feign调用商品微服务
        Product product = productService.findById(pid);
        if (product.getPid()==-1){
            Order order = new Order();
            order.setPname("下单失败");
            return order;
        }
        Order order = new Order();
        order.setUid(1);
        order.setPname("华为");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setNumber(10);
        orderService.save(order);
        rocketMQTemplate.convertAndSend("order-test-topic", order);
        return order;
    }
}
