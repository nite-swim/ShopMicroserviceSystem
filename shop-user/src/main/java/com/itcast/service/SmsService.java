package com.itcast.service;

import com.google.gson.Gson;
import com.itcast.pojo.Order;
import com.itcast.pojo.Param;
import com.itcast.utils.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "shop-user-test", topic = "order-test-topic")
public class SmsService implements RocketMQListener<Order> {
    @Autowired
    private Gson gson;

    @Override
    public void onMessage(Order order) {
        log.info("收到一个订单信息{}，接下来发送短信", order);
        Param param = new Param("商品订单测试系统");
        SmsUtil.sendSms("15035211661", "商品订单为服务系统", "SMS_475010585", gson.toJson(param));
    }
}
