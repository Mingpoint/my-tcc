package org.example.goods.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (ProductInfo)实体类
 *
 * @author makejava
 * @since 2021-02-23 16:51:16
 */
@Data
public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = -93188777345846633L;
    
    private Integer id;
    
    private String productId;
    
    private String productName;
    
    private String productImage;
    
    private Double productPrice;

}