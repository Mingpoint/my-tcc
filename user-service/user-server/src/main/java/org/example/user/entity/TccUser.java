package org.example.user.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (TTccUser)实体类
 *
 * @author makejava
 * @since 2021-02-24 17:20:03
 */
@Data
public class TccUser implements Serializable {
    private static final long serialVersionUID = -67414937903637274L;
    /**
    * 主键
    */
    private Integer id;
    
    private String uid;
    /**
    * 用户名
    */
    private String userName;
    /**
    * 电话
    */
    private String userPhone;

}