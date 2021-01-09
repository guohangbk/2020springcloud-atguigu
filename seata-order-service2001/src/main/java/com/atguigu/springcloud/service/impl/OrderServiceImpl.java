package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.OrderDao;
import com.atguigu.springcloud.domain.Order;
import com.atguigu.springcloud.service.AccountService;
import com.atguigu.springcloud.service.OrderService;
import com.atguigu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author guohang
 * @Description
 * @Date 2020/4/23 20:59
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StorageService storageService;

    @Override
    @GlobalTransactional(name="fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("--------------->开始新建订单");
        orderDao.create(order);

        log.info("--------------->订单微服务开始调用库存微服务，做扣减库存Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("--------------->订单微服务开始调用库存微服务，做扣减库存结束");

        log.info("--------------->订单微服务开始调用账户微服务，做扣钱Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("--------------->订单微服务开始调用账户微服务，做扣钱结束");

        log.info("--------------->订单微服务调用结束，修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("--------------->订单微服务调用结束，修改订单状态结束");

        log.info("--------------->下订单结束了，O(∩_∩)O哈哈~");

    }
}



