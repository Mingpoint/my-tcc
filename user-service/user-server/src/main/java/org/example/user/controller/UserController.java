package org.example.user.controller;

import org.example.common.rsp.HttpResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.user.proto.*;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/24 17:26
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.user.controller
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/frozenAmount")
    HttpResult<FrozenAmountResp> frozenAmount(@RequestBody FrozenAmountReq req) {
        return null;
    }
    @RequestMapping("/rollBackAmount")
    HttpResult<RollBackAmountResp> rollBackAmount(@RequestBody RollBackAmountReq req) {return null;}
    @RequestMapping("/writeOffAmount")
    HttpResult<WriteOffAmountResp> writeOffAmount(@RequestBody WriteOffAmountReq req) {return null;}
}
