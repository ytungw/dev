package com.ytung.order.feign;

import com.ytung.bean.order.Order;
import com.ytung.bean.product.Product;
import jakarta.websocket.server.PathParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-product")
public interface ProductFeign {
    @GetMapping("/product/{productId}")
    Product getProductById(@PathVariable("productId") long productId);
}
