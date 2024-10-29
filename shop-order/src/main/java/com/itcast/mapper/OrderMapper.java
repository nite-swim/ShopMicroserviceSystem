package com.itcast.mapper;

import com.itcast.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    @Insert("insert into shop_order (uid, pid ,pname, number) values (#{order.uid}, #{order.pid}, #{order.pname}, #{order.number})")
    void save(@Param("order") Order order);
}
