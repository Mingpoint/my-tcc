package org.example.order.proxys;

import org.example.common.rsp.HttpResult;
import org.example.order.proto.BuyReq;
import org.example.order.proto.BuyResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 18:06
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.order.proxys
 */
@FeignClient(value = "order-service", contextId = "OrderProxy")
@RequestMapping("/order")
public interface OrderProxy {
    @PostMapping("/createOrder")
    HttpResult<BuyResp> createOrder (@RequestBody BuyReq req);
}
