package com.ytung.product.service.impl;

import com.ytung.bean.product.Product;
import com.ytung.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(long productId) {
        Product product = new Product();
        product.setProductId(productId)
        	.setProductName("iPhone17")
        	.setNum(2)
        	.setPrice(new BigDecimal("5999"))
        	;



//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        return product;
    }
}
