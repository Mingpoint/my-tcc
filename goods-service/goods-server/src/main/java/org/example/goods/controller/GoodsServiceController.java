package org.example.goods.controller;

import org.example.common.rsp.HttpResult;
import org.example.goods.proto.*;
import org.example.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 10:57
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.goods.proxys
 */
@RestController
@RequestMapping("/goods")
public class GoodsServiceController {
    @Autowired
    private GoodsService goodsService;
    @PostMapping("/findGoodsInfo")
    public HttpResult<FindGoodsInfoResp> findGoodsInfo(@RequestBody FindGoodsInfoReq req) {
        return new HttpResult(goodsService.searchGoodsInfo(req));
    }
    @PostMapping("/frozenGoodsStore")
    public HttpResult<FrozenGoodsStoreResp> frozenGoodsStore(@RequestBody FrozenGoodsStoreReq req) {
        return new HttpResult(goodsService.frozenGoodsStoreService(req));
    }
    @PostMapping("/writeOffGoodsStore")
    public HttpResult<WriteOffGoodsStoreResp> writeOffGoodsStore(@RequestBody WriteOffGoodsStoreReq req) {
        return new HttpResult(goodsService.writeOffGoodsStoreService(req));
    }
    @PostMapping("/rollBackGoodsStore")
    public HttpResult<RollBackGoodsStoreResp> rollBackGoodsStore(@RequestBody RollBackGoodsStoreReq req) {
        return new HttpResult(goodsService.rollBackGoodsStoreService(req));
    }
    @PostMapping("/goods/buyGoods")
    public HttpResult<BuyGoodsResp> buyGoods(@RequestBody BuyGoodsReq req) {
        return new HttpResult<>(goodsService.buyGoodsService(req));

    }
}
