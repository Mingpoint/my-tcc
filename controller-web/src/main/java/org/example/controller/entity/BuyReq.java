package org.example.controller.entity;

import lombok.Data;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 10:42
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.controller.entity
 */
@Data
public class BuyReq {
    private String uid;
    private String productId;
    private String quantity;

}
