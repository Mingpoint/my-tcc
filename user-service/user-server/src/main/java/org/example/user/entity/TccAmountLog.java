package org.example.user.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (TTccAmountLog)实体类
 *
 * @author makejava
 * @since 2021-02-24 17:19:38
 */
@Data
public class TccAmountLog implements Serializable {
    private static final long serialVersionUID = -28805488741591111L;
    
    private Integer id;
    
    private Long quantity;
    
    private Date exeTime;
    
    private String uid;
    
    private Integer type;
    private String  trackNo;
}