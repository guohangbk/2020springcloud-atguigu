package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/19 22:09
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "--------------PaymentFallbackService----paymentInfo_OK,fallback****";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "--------------PaymentFallbackService----paymentInfo_Timeout,fallback****";
    }
}



