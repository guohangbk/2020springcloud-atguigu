package com.atguigu.springcloud.dao;

import com.atguigu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/23 20:41
 */
@Mapper
public interface OrderDao {

    //新建订单
    void create(Order order);

    //修改状态，从0改为1
    void update(@Param("userId") Long userId,@Param("status") Integer status);


}



