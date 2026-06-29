package com.ytung.order;

import com.ytung.bean.product.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void test(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                System.out.println(service+":"+instance.getHost()+":"+instance.getPort());
            }
        }
    }

    //测试负载均衡
    @Test
    void testLoadBalancer(){
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        System.out.println(choose.getHost()+choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println(choose.getHost()+choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println(choose.getHost()+choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println(choose.getHost()+choose.getPort());
        choose = loadBalancerClient.choose("service-product");
        System.out.println(choose.getHost()+choose.getPort());
    }

    @Test
    void getProductFromRemoteBalancedAnnotation() {
        String url = "http://service-product/product/" + 1;
        System.out.println(restTemplate.getForObject(url, Product.class));
    }



}
