package com.ytung.order.controller;

import com.ytung.bean.order.Order;
import com.ytung.order.properties.OrderProperties;
import com.ytung.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//@RefreshScope
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProperties orderProperties;

//    @Value("${order.timeout}")
//    String orderTimeOut;
//
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;

//    @GetMapping("/config")
//    public String config(){
//        return orderTimeOut+"-"+orderAutoConfirm;
//    }

    @GetMapping("/config")
    public String config(){
        return orderProperties.getTimeOut()+"-"+orderProperties.getAutoConfirm();
    }


    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") long userId, @RequestParam("productId") long productId) {
        return orderService.createOrder(userId,productId);
    }
}
