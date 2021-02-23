package org.example.order.controller;

import org.example.common.rsp.HttpResult;
import org.example.order.proto.BuyReq;
import org.example.order.proto.BuyResp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 18:04
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.order.controller
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @PostMapping("/buy")
    HttpResult<BuyResp> buy(@RequestBody BuyReq req) {
        return null;
    }
}
