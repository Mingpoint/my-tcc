package org.example.goods.proxys;

import org.example.common.rsp.HttpResult;
import org.example.goods.proto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 10:57
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.goods.proxys
 */
@FeignClient(value = "goods-service", contextId = "GoodsServiceProxy")
public interface GoodsServiceProxy {
    @PostMapping("/goods/findGoodsInfo")
    HttpResult<FindGoodsInfoResp> findGoodsInfo(@RequestBody FindGoodsInfoReq req);
    @PostMapping("/goods/frozenGoodsStore")
    HttpResult<FrozenGoodsStoreResp> frozenGoodsStore(@RequestBody FrozenGoodsStoreReq req);
    @PostMapping("/goods/writeOffGoodsStore")
    HttpResult<WriteOffGoodsStoreResp> writeOffGoodsStore(@RequestBody WriteOffGoodsStoreReq req);
    @PostMapping("/goods/rollBackGoodsStore")
    HttpResult<RollBackGoodsStoreResp> rollBackGoodsStore(@RequestBody RollBackGoodsStoreReq req);
}
