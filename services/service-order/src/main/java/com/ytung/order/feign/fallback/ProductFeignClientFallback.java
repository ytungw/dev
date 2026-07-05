package com.ytung.order.feign.fallback;

import com.ytung.bean.product.Product;
import com.ytung.order.feign.ProductFeign;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeign {
    @Override
    public Product getProductById(long productId) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("未知商品");
        product.setNum(0);
        product.setPrice(new BigDecimal("0"));
        return product;

    }
}
