package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/20 10:18
 */
@Configuration
public class GetWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_atguigu",
                r -> r.path("/guonei").
                        uri("http://news.baidu.com/guonei")).build();
        routes.route("path_route_atguigu1",
                r -> r.path("/guoji").
                        uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }





}



