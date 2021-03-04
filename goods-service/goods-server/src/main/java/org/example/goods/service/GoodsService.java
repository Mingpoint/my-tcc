package org.example.goods.service;

import org.example.common.exceptions.TccException;
import org.example.core.annotation.TccAnnotation;
import org.example.goods.dao.GoodsDao;
import org.example.goods.dao.GoodsStockDao;
import org.example.goods.entity.GoodsInfo;
import org.example.goods.entity.GoodsStock;
import org.example.goods.proto.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 16:37
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.goods.service
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsStockDao goodsStockDao;
    public FindGoodsInfoResp searchGoodsInfo (FindGoodsInfoReq req) {
        FindGoodsInfoResp findGoodsInfoResp = new FindGoodsInfoResp();
        GoodsInfo productInfo = goodsDao.queryGoodsInfo(req.getProductId());
        BeanUtils.copyProperties(productInfo,findGoodsInfoResp);
        return findGoodsInfoResp;
    }
    public FrozenGoodsStoreResp frozenGoodsStoreService (FrozenGoodsStoreReq req) {
        FrozenGoodsStoreResp frozenGoodsStoreResp = new FrozenGoodsStoreResp();
        GoodsStock productStock = goodsStockDao.queryGoodsStore(req.getProductId());
        Integer stockQuantity = productStock.getStockQuantity();
        Integer diff = stockQuantity - req.getQuantity();
        if (diff < 0) {
            throw new TccException("LowStocks","库存不足");
        }
        Integer frozenQuantity = productStock.getFrozenQuantity();
        boolean b = goodsStockDao.updateFrozenGoodsStore(req.getProductId(), stockQuantity - req.getQuantity(), frozenQuantity + req.getQuantity());
        frozenGoodsStoreResp.setStatus(b);
        return frozenGoodsStoreResp;
    }

    public WriteOffGoodsStoreResp writeOffGoodsStoreService (WriteOffGoodsStoreReq req) {
        WriteOffGoodsStoreResp writeOffGoodsStoreResp = new WriteOffGoodsStoreResp();
        GoodsStock productStock = goodsStockDao.queryGoodsStore(req.getProductId());
        Integer soldQuantity = productStock.getSoldQuantity();

        Integer frozenQuantity = productStock.getFrozenQuantity();
        boolean b = goodsStockDao.updateWriteOffGoodsStore(req.getProductId(), soldQuantity + req.getQuantity(), frozenQuantity - req.getQuantity());
        writeOffGoodsStoreResp.setStatus(b);
        return writeOffGoodsStoreResp;
    }
    public RollBackGoodsStoreResp rollBackGoodsStoreService (RollBackGoodsStoreReq req) {
        RollBackGoodsStoreResp rollBackGoodsStoreResp = new RollBackGoodsStoreResp();
        GoodsStock productStock = goodsStockDao.queryGoodsStore(req.getProductId());
        Integer soldQuantity = productStock.getSoldQuantity();
        Integer frozenQuantity = productStock.getFrozenQuantity();
        Integer stockQuantity = productStock.getStockQuantity();

        boolean b = goodsStockDao.updateRollBackGoodsStore(req.getProductId(), stockQuantity + req.getQuantity(),soldQuantity - req.getQuantity(), frozenQuantity - req.getQuantity());
        rollBackGoodsStoreResp.setStatus(b);
        return rollBackGoodsStoreResp;
    }

    @TccAnnotation(confirmMethod = "buyGoodsConfirmService",rollBackMethod="buyGoodsRollBackService")
    public BuyGoodsResp buyGoodsService (BuyGoodsReq req) {
        BuyGoodsResp buyGoodsResp = new BuyGoodsResp();
        FrozenGoodsStoreReq request = new FrozenGoodsStoreReq ();
        request.setQuantity(req.getQuantity());
        request.setProductId(req.getProductId());
        FrozenGoodsStoreResp frozenGoodsStoreResp = this.frozenGoodsStoreService(request);
        if (!frozenGoodsStoreResp.isStatus()) {
            throw new TccException("lockStoreFail","库存锁定失败");
        }
        return buyGoodsResp;
    }
    public BuyGoodsResp buyGoodsConfirmService (BuyGoodsReq req) {
        BuyGoodsResp buyGoodsResp = new BuyGoodsResp();
        WriteOffGoodsStoreReq request = new WriteOffGoodsStoreReq ();
        request.setQuantity(req.getQuantity());
        request.setProductId(req.getProductId());
        WriteOffGoodsStoreResp writeOffGoodsStoreResp = this.writeOffGoodsStoreService(request);
        if (!writeOffGoodsStoreResp.isStatus()) {
            throw new TccException("writeOffGoodsStoreFail","库存核销失败");
        }
        return buyGoodsResp;
    }
    public BuyGoodsResp buyGoodsRollBackService (BuyGoodsReq req) {
        BuyGoodsResp buyGoodsResp = new BuyGoodsResp();
        RollBackGoodsStoreReq request = new RollBackGoodsStoreReq ();
        request.setQuantity(req.getQuantity());
        request.setProductId(req.getProductId());
        RollBackGoodsStoreResp rollBackGoodsStoreResp = this.rollBackGoodsStoreService(request);
        if (!rollBackGoodsStoreResp.isStatus()) {
            throw new TccException("rollBackGoodsStoreFail","库存回滚失败");
        }
        return buyGoodsResp;
    }
}
