package com.ytung.bean.order;

import com.ytung.bean.product.Product;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Data
@Accessors(chain = true)
public class Order {
    private long orderId;
    private long userId;
    private String nickName;
    private String address;
    private List<Product> products;
    private BigDecimal totalPrice;
}
