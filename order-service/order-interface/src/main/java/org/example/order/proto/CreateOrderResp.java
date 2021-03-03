package org.example.order.proto;

import lombok.Data;
import org.aspectj.weaver.ast.Or;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 18:15
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.order.proto
 */
@Data
public class CreateOrderResp extends OrderBase {
    private boolean status;
}
