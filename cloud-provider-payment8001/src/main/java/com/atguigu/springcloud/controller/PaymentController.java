package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/18 17:19
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 服务发现，获取服务信息
     */
    @Resource
    private DiscoveryClient discoveryClient;

    /**
    * @Description: 创建支付记录
    * @Author: guohang
    * @Date: 2020/4/18 17:26
    * @Param: [payment]
    * @return: com.atguigu.springcloud.entities.CommonResult
    */
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果" + result);
        if (result > 0){
            return new CommonResult(200,"插入数据库成功，提供服务的端口号："+serverPort,result);
        }else{
            return new CommonResult(444,"插入数据库失败！");
        }
    }

    /**
    * @Description: 根据id查询支付记录
    * @Author: guohang
    * @Date: 2020/4/18 17:26
    * @Param: [id]
    * @return: com.atguigu.springcloud.entities.CommonResult
    */
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果" + payment);
        if (null != payment){
            return new CommonResult(200,"查询数据库成功，提供服务的端口号："+serverPort,payment);
        }else{
            return new CommonResult(444,"没有查询到"+id+"的数据！");
        }
    }


    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> instances = discoveryClient.getServices();
        for (String instance : instances) {
            log.info("*****所有的服务信息：" + instance);
        }
        List<ServiceInstance> instanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance serviceInstance : instanceList) {
            log.info(serviceInstance.getServiceId() + "\t" + serviceInstance.getHost() + "\t" + serviceInstance.getPort() + "\t" + serviceInstance.getUri());
        }
        return this.discoveryClient;
    }


    @GetMapping(value="/payment/lb")
    public String getPayment(){
        return serverPort;
    }


    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }


    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi,i`am paymentzipkin server fall back.welcome to atguigu.hahahahahhahahah";
    }

}



