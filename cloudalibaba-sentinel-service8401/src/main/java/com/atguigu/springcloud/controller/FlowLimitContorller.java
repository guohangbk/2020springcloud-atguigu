package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/23 10:00
 */
@RestController
@Slf4j
public class FlowLimitContorller {

    @GetMapping("/testA")
    public String testA(){
        return "--------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName() + "\t" + "...testB" );
        return "--------testB";
    }

    @GetMapping("/testC")
    public String testC(){
        log.info(Thread.currentThread().getName() + "\t" + "--------testC异常比例测试" );
        int a = 10/0;
        return "--------testC异常比例测试";
    }

    @GetMapping("/testD")
    public String testD() {
        log.info(Thread.currentThread().getName() + "\t" + "...testD" );
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "----- testD";
    }

    @GetMapping("/testE")
    public String testE(){
        log.info(Thread.currentThread().getName() + "\t" + "--------testE异常数测试" );
        int a = 10/0;
        return "--------testE异常数测试";
    }


    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHostkey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        return "-----testHotKey";
    }

    public String deal_testHostkey(String p1, String p2, BlockException e){
        return "-----deal_testHotKey";
    }




}



