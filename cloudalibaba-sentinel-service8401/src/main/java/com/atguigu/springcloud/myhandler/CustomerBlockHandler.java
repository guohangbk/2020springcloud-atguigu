package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/23 15:14
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(  444, "按客户自定义的处理方式----1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(  444, "按客户自定义的处理方式----2");
    }

}



