package org.example.goods.proto;

import lombok.Data;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 11:13
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.goods.proto
 */
@Data
public class WriteOffGoodsStoreReq extends GoodsBaseInfo {
    private Integer quantity;

}
