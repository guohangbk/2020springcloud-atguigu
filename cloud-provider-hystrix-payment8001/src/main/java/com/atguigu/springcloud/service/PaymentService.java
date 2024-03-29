package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/19 17:26
 */
@Service
public class PaymentService {

    /**
     * 正常访问的方法
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK,id:" + id + "\t" + "O(∩_∩)O哈哈~";
    }


    /**
     * 非正常访问，1.5秒以内走正常逻辑，超过1.5秒未响应的话调用兜底的paymentInfo_TimeoutHanlder方法给出服务降级的提醒
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHanlder", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id){
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_Timeout,id:" + id + "\t耗时：" + "\tO(∩_∩)O哈哈~";
    }

    public String paymentInfo_TimeoutHanlder(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "8001系统繁忙，请稍后再试！,id:" + id + "\t我是兜底的***┭┮﹏┭┮\"~";

    }


   //*******************服务熔断*******************
   @HystrixCommand(fallbackMethod="paymentCircuitBreakerFallback", commandProperties={
           @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"),//是否开启断路器
           @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
           @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期 "
           @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸
   })
   public String paymentCircuitBreaker(@PathVariable("id") Integer id){
       if (id < 0){
           throw new RuntimeException("*******id不能为负数");
       }
       String serialNumber = IdUtil.simpleUUID();
       return Thread.currentThread().getName()+"\t "+"调用成功,流水号: "+serialNumber;
   }
   public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id){
        return "id不能为负数,请稍后再试~ id: "+ id;
    }






}



