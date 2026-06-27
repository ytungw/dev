package com.ytung.bean.product;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class Product {
    private long productId;
    private String productName;
    private long num;
    private BigDecimal price;
}
