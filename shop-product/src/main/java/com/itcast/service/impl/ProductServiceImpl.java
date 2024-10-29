package com.itcast.service.impl;

import com.google.gson.Gson;
import com.itcast.mapper.ProductMapper;
import com.itcast.pojo.Param;
import com.itcast.pojo.Param2;
import com.itcast.pojo.Product;
import com.itcast.service.ProductService;
import com.itcast.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private Gson gson;
    @Override
    public Product findById(int id) {
        Product product = productMapper.findByPid(1);
/*        Param param = new Param("骚扰短信打扰中心");
        SmsUtil.sendSms("15103422788", "商品订单为服务系统", "SMS_475010585", gson.toJson(param));*/
        /*Param2 param2 = new Param2("季东先生","季博伟");
        SmsUtil.sendSms("13994358003", "商品订单为服务系统", "SMS_474780663", gson.toJson(param2));*/
        return product;
    }
}
