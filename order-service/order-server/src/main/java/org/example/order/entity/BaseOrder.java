package org.example.order.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (BaseOrder)实体类
 *
 * @author makejava
 * @since 2021-02-23 18:01:50
 */
@Data
public class BaseOrder implements Serializable {
    private static final long serialVersionUID = -80195120053474829L;
    
    private Integer id;
    
    private String orderNo;
    
    private String uid;
    
    private Double payAmount;
    
    private Integer orderStatus;
    
    private Integer isDeleted;

}