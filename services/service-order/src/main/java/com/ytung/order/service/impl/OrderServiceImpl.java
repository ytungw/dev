package com.ytung.order.service.impl;

import com.ytung.bean.order.Order;
import com.ytung.bean.product.Product;
import com.ytung.order.feign.ProductFeign;
import com.ytung.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductFeign productFeign;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public Order createOrder(long userId, long productId) {
        Product productById = getProductFromRemoteBalancedAnnotation(productId);
        Order order = new Order();
        order.setOrderId(2L)
                .setUserId(userId)
                .setNickName("su")
                .setAddress("重庆")
                .setProducts(Arrays.asList(productById))
                .setTotalPrice(productById.getPrice().multiply(new BigDecimal(productById.getNum())))
        ;
        return order;
    }

    //通过注解的形式实现远程调用

    private Product getProductFromRemoteBalancedAnnotation(long productId) {
        String url = "http://service-product/product/" + productId;
        Product productById = restTemplate.getForObject(url, Product.class);
        return productById;
    }


    private Product getProductFromRemoteBalancer(long productId) {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("远程请求{}", url);
        Product productById = restTemplate.getForObject(url, Product.class);
        return productById;

    }

    private Product getProductFromRemote(long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance productInstance = instances.get(0);
        String url = "http://" + productInstance.getHost() + ":" + productInstance.getPort() + "/product/" + productId;
        log.info("远程请求{}", url);
        Product productById = restTemplate.getForObject(url, Product.class);
        return productById;
    }
}
