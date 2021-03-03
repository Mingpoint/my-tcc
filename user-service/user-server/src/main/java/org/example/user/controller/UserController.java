package org.example.user.controller;

import org.example.common.rsp.HttpResult;
import org.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserService userService;
    @RequestMapping("/frozenAmount")
    public HttpResult<FrozenAmountResp> frozenAmount(@RequestBody FrozenAmountReq req) {
        return new HttpResult<>(userService.frozenAmount(req));
    }
    @RequestMapping("/rollBackAmount")
    public HttpResult<RollBackAmountResp> rollBackAmount(@RequestBody RollBackAmountReq req) {
        return new HttpResult<>(userService.rollBackAmount(req));
    }
    @RequestMapping("/writeOffAmount")
    public HttpResult<WriteOffAmountResp> writeOffAmount(@RequestBody WriteOffAmountReq req) {
        return new HttpResult<>(userService.writeOffAmount(req));
    }
}
