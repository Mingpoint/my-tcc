package org.example.goods.proto;

import lombok.Data;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 10:58
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.goods.proto
 */
@Data
public class FindGoodsInfoResp {
    private String productId;
    private String productName;
    private String productImage;
    private Double productPrice;
    private Integer stockQuantity;
    private Integer frozenQuantity;
    private Integer soldQuantity;
}
