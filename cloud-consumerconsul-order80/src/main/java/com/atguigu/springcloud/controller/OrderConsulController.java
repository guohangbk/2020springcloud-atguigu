package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/19 11:36
 */
@Slf4j
@RestController
public class OrderConsulController {

    public static final String PAYMENT_URL="http://consul-provider-payment";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("consumer/payment/consul")
    public String paymentInfo(){
        String template = restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
        return template;
    }

}



