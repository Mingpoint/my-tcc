package org.example.order.proto;

import lombok.Data;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 18:11
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.order.proto
 */
@Data
public class Item extends OrderBase {
    private String productName;
    private String productPrice;
    private String productImage;
    private String productId;
    private Integer buyQuantity;
}
