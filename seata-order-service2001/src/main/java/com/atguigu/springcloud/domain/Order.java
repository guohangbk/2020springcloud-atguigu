package com.atguigu.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/23 20:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    private Integer status; //0创建中，1已完成


}



