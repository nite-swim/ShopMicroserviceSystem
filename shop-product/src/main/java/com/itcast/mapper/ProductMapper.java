package com.itcast.mapper;

import com.itcast.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    @Select("select * from shop_product where pid = #{pid}")
    Product findByPid(int pid);
}
