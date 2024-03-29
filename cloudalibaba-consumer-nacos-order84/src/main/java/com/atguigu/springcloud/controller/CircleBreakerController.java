package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/23 15:56
 */
@RestController
public class CircleBreakerController {

    @Value("${server.port}")
    private String serverPost;

    public static final String SERVICE_URL="http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler") //handlerFallback和blockHandler都配置
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL+"/paymentSQL/"+id,CommonResult.class,id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return result;
    }

    /**
     * 程序运行异常
     * @param id
     * @param e
     * @return
     */
    public CommonResult handlerFallback(@PathVariable("id") Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"兜底异常handlerFallback,exception内容"+e.getMessage(),payment);
    }

    /**
     * 配置异常
     * @param id
     * @param exception
     * @return
     */
    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException exception) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(445,"兜底异常handlerFallback,exception内容"+exception.getMessage(),payment);
    }



    //=============================openFeign

    @Resource
    private PaymentService paymentService;


    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }






}




