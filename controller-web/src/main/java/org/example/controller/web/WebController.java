package org.example.controller.web;

import org.example.controller.entity.BuyReq;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 10:41
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.controller.web
 */
@RestController
@RequestMapping("/web")
public class WebController {
    @RequestMapping("/buy")
    public String buy (@RequestBody BuyReq buyReq) {
        //1.查询商品信息
        //2.锁定库存
        //3.查询用户金额/回滚库存
        //4.冻结金额
        //5.下单
        //6.核销库存/回滚
        //7.核销金额/回滚
        return null;
    }
}
