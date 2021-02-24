package org.example.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (TTccAmount)实体类
 *
 * @author makejava
 * @since 2021-02-24 17:19:16
 */
@Data
public class TccAmount implements Serializable {
    private static final long serialVersionUID = 837298145063499643L;
    
    private Integer id;
    /**
    * 有效金额
    */
    private Long availableQuantity;
    /**
    * 冻结金额
    */
    private Long frozenQuantity;
    /**
    * 有户id
    */
    private Integer uid;



}