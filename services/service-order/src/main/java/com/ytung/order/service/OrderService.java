package com.ytung.order.service;

import com.ytung.bean.order.Order;

public interface OrderService {
    Order createOrder(long userId, long productId);
}
