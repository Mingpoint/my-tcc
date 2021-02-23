package org.example.order.proto;

import lombok.Data;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 18:07
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.order.proto
 */
@Data
public class BuyReq extends OrderBase {
    private String productId;
    private Integer buyQuantity;

    private String uid;

    private Double payAmount;
    private Item item;
}
