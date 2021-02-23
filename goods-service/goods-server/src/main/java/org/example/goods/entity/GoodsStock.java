package org.example.goods.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (ProductStock)实体类
 *
 * @author makejava
 * @since 2021-02-23 16:52:16
 */
@Data
public class GoodsStock implements Serializable {
    private static final long serialVersionUID = 665431053922652808L;
    
    private Integer id;
    
    private String productId;
    /**
    * 库存数
    */
    private Integer stockQuantity;
    /**
    * 已冻结库存
    */
    private Integer frozenQuantity;
    /**
    * 已售库存
    */
    private Integer soldQuantity;


}