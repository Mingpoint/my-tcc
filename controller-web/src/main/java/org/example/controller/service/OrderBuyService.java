package org.example.controller.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.rsp.HttpResult;
import org.example.controller.entity.BuyReq;
import org.example.goods.proto.*;
import org.example.goods.proxys.GoodsServiceProxy;
import org.example.order.proto.CreateOrderReq;
import org.example.order.proto.CreateOrderResp;
import org.example.order.proxys.OrderProxy;
import org.springframework.stereotype.Service;
import org.user.proto.*;
import org.user.proxy.UserProxy;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/26 15:11
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.controller.service
 */
@Slf4j
@Service
public class OrderBuyService {
    @Resource
    private GoodsServiceProxy goodsServiceProxy;
    @Resource
    private UserProxy userProxy;
    @Resource
    private OrderProxy orderProxy;
    public String buy (BuyReq req) {
            //1.查询商品信息
            FindGoodsInfoReq findGoodsInfoReq = new FindGoodsInfoReq();
            findGoodsInfoReq.setProductId(req.getProductId());
            HttpResult<FindGoodsInfoResp> goodsInfo = goodsServiceProxy.findGoodsInfo(findGoodsInfoReq);
            FindGoodsInfoResp findGoodsInfoData = goodsInfo.getData();
        try {
            //2.锁定库存
            FrozenGoodsStoreReq frozenGoodsStoreReq = new FrozenGoodsStoreReq();
            frozenGoodsStoreReq.setProductId(req.getProductId());
            frozenGoodsStoreReq.setQuantity(req.getQuantity());
            HttpResult<FrozenGoodsStoreResp> frozenGoodsStoreRespHttpResult = goodsServiceProxy.frozenGoodsStore(frozenGoodsStoreReq);
            FrozenGoodsStoreResp frozenGoodsStoreData = frozenGoodsStoreRespHttpResult.getData();
            boolean status = frozenGoodsStoreData.isStatus();
            if (!status) {
                log.error("锁定库存失败");
                return null;
            }
        } catch (Exception ex) {
            log.error("锁定库存异常",ex);
            return null;
        }
        Double productPrice = findGoodsInfoData.getProductPrice();
        long amount = (long)(productPrice * req.getQuantity());
        String trackNo = null;
        boolean frozenAmountStatus =  true;
        try {
            //3.冻结金额
            FrozenAmountReq frozenAmountReq = new FrozenAmountReq();
            frozenAmountReq.setUid(req.getUid());
            frozenAmountReq.setFrozenAmount(amount);
            HttpResult<FrozenAmountResp> frozenAmountResp = userProxy.frozenAmount(frozenAmountReq);
            FrozenAmountResp frozenAmountData = frozenAmountResp.getData();
            trackNo = frozenAmountData.getTrackNo();
            frozenAmountStatus = frozenAmountData.isStatus();
        } catch (Exception ex) {
            log.error("锁定金额异常",ex);
            frozenAmountStatus = false;
            return null;
        }

        if (!frozenAmountStatus) {
            RollBackGoodsStoreReq rollBackGoodsStoreReq = new RollBackGoodsStoreReq();
            rollBackGoodsStoreReq.setProductId(req.getProductId());
            rollBackGoodsStoreReq.setQuantity(req.getQuantity());
            log.error("回滚库存");
            goodsServiceProxy.rollBackGoodsStore(rollBackGoodsStoreReq);
            return null;
        }



        String orderNo = null;
        boolean createOrderStatus = true;
        try {
            //4.下单
            CreateOrderReq createOrderReq = new CreateOrderReq();
            HttpResult<CreateOrderResp> createOrderResp = orderProxy.createOrder(createOrderReq);
            CreateOrderResp createOrderData = createOrderResp.getData();
            orderNo = createOrderData.getOrderNo();
            createOrderStatus = createOrderData.isStatus();
        } catch (Exception ex) {
            log.error("创建订单异常",ex);
            createOrderStatus = false;
        }
        if (!createOrderStatus) {
            RollBackGoodsStoreReq rollBackGoodsStoreReq = new RollBackGoodsStoreReq();
            rollBackGoodsStoreReq.setProductId(req.getProductId());
            rollBackGoodsStoreReq.setQuantity(req.getQuantity());
            log.error("回滚库存");
            goodsServiceProxy.rollBackGoodsStore(rollBackGoodsStoreReq);
            RollBackAmountReq rollBackAmountReq = new RollBackAmountReq();
            rollBackAmountReq.setTrackNo(trackNo);
            rollBackAmountReq.setUid(req.getUid());
            rollBackAmountReq.setRollBackAmount(amount);
            log.error("回滚金额");
            userProxy.rollBackAmount(rollBackAmountReq);
            return null;
        }
        boolean writeOffGoodsStoreStatus = true;
        try {
            //5.核销库存/回滚
            WriteOffGoodsStoreReq writeOffGoodsStoreReq = new WriteOffGoodsStoreReq();
            writeOffGoodsStoreReq.setProductId(req.getProductId());
            writeOffGoodsStoreReq.setQuantity(req.getQuantity());
            HttpResult<WriteOffGoodsStoreResp> writeOffGoodsStoreResp = goodsServiceProxy.writeOffGoodsStore(writeOffGoodsStoreReq);
            writeOffGoodsStoreStatus = writeOffGoodsStoreResp.getData().isStatus();
        } catch (Exception ex) {
            log.error("核销库存异常",ex);
            writeOffGoodsStoreStatus = false;
        }
        if (!writeOffGoodsStoreStatus) {
            RollBackGoodsStoreReq rollBackGoodsStoreReq = new RollBackGoodsStoreReq();
            rollBackGoodsStoreReq.setProductId(req.getProductId());
            rollBackGoodsStoreReq.setQuantity(req.getQuantity());
            log.error("回滚库存");
            goodsServiceProxy.rollBackGoodsStore(rollBackGoodsStoreReq);
            RollBackAmountReq rollBackAmountReq = new RollBackAmountReq();
            rollBackAmountReq.setTrackNo(trackNo);
            rollBackAmountReq.setUid(req.getUid());
            rollBackAmountReq.setRollBackAmount(amount);
            log.error("回滚金额");
            userProxy.rollBackAmount(rollBackAmountReq);
            return null;
        }
        boolean writeOffAmountStatus = true;
        try {
            //6.核销金额/回滚
            WriteOffAmountReq writeOffAmountReq = new WriteOffAmountReq();
            writeOffAmountReq.setTrackNo(trackNo);
            writeOffAmountReq.setUid(req.getUid());
            HttpResult<WriteOffAmountResp> writeOffAmountResp = userProxy.writeOffAmount(writeOffAmountReq);
            writeOffAmountStatus = writeOffAmountResp.getData().isStatus();
        } catch (Exception ex) {
            log.error("核销库存异常",ex);
            writeOffGoodsStoreStatus = false;
        }
//        if (!writeOffAmountStatus) {
//            RollBackGoodsStoreReq rollBackGoodsStoreReq = new RollBackGoodsStoreReq();
//            rollBackGoodsStoreReq.setProductId(req.getProductId());
//            rollBackGoodsStoreReq.setQuantity(req.getQuantity());
//            log.error("回滚库存");
//            goodsServiceProxy.rollBackGoodsStore(rollBackGoodsStoreReq);
//            RollBackAmountReq rollBackAmountReq = new RollBackAmountReq();
//            rollBackAmountReq.setTrackNo(trackNo);
//            rollBackAmountReq.setUid(req.getUid());
//            rollBackAmountReq.setRollBackAmount(amount);
//            log.error("回滚金额");
//            userProxy.rollBackAmount(rollBackAmountReq);
//            return null;
//        }
        return null;
    }
}
