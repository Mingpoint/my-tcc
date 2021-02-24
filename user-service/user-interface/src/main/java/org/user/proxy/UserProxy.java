package org.user.proxy;

import org.example.common.rsp.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.user.proto.*;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/24 17:28
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.user.proxy
 */
@FeignClient(value = "user-service", contextId = "UserProxy")
@RequestMapping("/user")
public interface UserProxy {
    @RequestMapping("/frozenAmount")
    HttpResult<FrozenAmountResp> frozenAmount(@RequestBody FrozenAmountReq req);
    @RequestMapping("/rollBackAmount")
    HttpResult<RollBackAmountResp> rollBackAmount(@RequestBody RollBackAmountReq req);
    @RequestMapping("/writeOffAmount")
    HttpResult<WriteOffAmountResp> writeOffAmount(@RequestBody WriteOffAmountReq req);
}
