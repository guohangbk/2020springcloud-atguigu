package com.atguigu.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/23 15:36
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"e13bc873d0354fbf9b40575042a69684"));
        hashMap.put(2L,new Payment(2L,"8caf54a6541644508cea8f486c3d9130"));
        hashMap.put(3L,new Payment(3L,"1b989afce0304f87bb26ca625650b9df"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        CommonResult commonResult = new CommonResult(200, "from mysql, serverPort:" + serverPort,payment);
        return commonResult;
    }

}



