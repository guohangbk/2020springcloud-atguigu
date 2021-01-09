package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author guohang
 * @Description 支付模块的主启动类
 * @Date 2020/4/18 16:44
 */
@EnableEurekaClient //标注为这个是Eureka的客户端
@SpringBootApplication
@EnableDiscoveryClient  //服务自动发现
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}

