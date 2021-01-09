package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author guohang
 * @Description 自己手写负载均衡算法
 * @Date 2020/4/19 14:43
 */
public interface Loadbalancer {

    ServiceInstance instance (List<ServiceInstance> serviceInstances);

}
