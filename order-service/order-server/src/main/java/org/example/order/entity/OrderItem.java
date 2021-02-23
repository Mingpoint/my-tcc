package org.example.order.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (OrderItem)实体类
 *
 * @author makejava
 * @since 2021-02-23 18:03:00
 */
@Data
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 186768621343810221L;
    
    private Integer id;
    
    private String orderNo;
    
    private String productId;
    
    private Integer buyQuantity;

    private String productName;
    private String productPrice;
    private String productImage;
}