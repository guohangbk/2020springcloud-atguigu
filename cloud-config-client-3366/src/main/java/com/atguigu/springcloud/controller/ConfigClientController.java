package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/20 13:55
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;  //要访问的3344上的信息

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/configInfo")	//请求地址
    public String getConfigInfo(){
        return "serverPort:" + serverPort + "\t\n\n configInfo:" +configInfo;
    }



}