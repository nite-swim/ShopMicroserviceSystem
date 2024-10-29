package com.itcast.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long oid;//订单id
    private Integer uid;//用户id
    private Integer pid;//商品id
    private String pname;//商品名
    private Integer number;
}
