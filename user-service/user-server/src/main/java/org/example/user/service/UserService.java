package org.example.user.service;

import org.example.common.enums.OrderLogType;
import org.example.common.exceptions.TccException;
import org.example.user.dao.AmountDao;
import org.example.user.dao.AmountLogDao;
import org.example.user.entity.TccAmount;
import org.example.user.entity.TccAmountLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.user.proto.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/24 17:43
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.user.service
 */
@Service
public class UserService {
    @Autowired
    private AmountDao amountDao;
    @Autowired
    private AmountLogDao amountLogDao;
    @Transactional
    public FrozenAmountResp frozenAmount(FrozenAmountReq req) {
        FrozenAmountResp frozenAmountResp = new FrozenAmountResp();
        TccAmount tccAmount = new TccAmount();
        TccAmount tcc = this.searchAmount(req.getUid());
        Long diff = tcc.getAvailableQuantity() - req.getFrozenAmount();
        if (diff < 0) {
            throw new TccException("AmountEnough","金额不足");
        }
        tccAmount.setUid(req.getUid());
        tccAmount.setFrozenQuantity(tcc.getFrozenQuantity()+req.getFrozenAmount());
        tccAmount.setAvailableQuantity(diff);
        boolean a = amountDao.updateAmount(tccAmount);

        TccAmountLog tLog = new TccAmountLog ();
        tLog.setExeTime(new Date());
        tLog.setQuantity(req.getFrozenAmount());
        tLog.setType(OrderLogType.FROZEN.getValue());
        tLog.setUid(req.getUid());
        String trackNo = UUID.randomUUID().toString().replaceAll("-", "");
        tLog.setTrackNo(trackNo);
        boolean b = amountLogDao.insertAmountLogDao(tLog);
        if (!a || !b) {
            throw new TccException("SqlError","插入数据库异常");
        }
        frozenAmountResp.setStatus(a && b);
        frozenAmountResp.setTrackNo(trackNo);
        return frozenAmountResp;
    }

    @Transactional
    public RollBackAmountResp rollBackAmount(RollBackAmountReq req) {
        RollBackAmountResp rollBackAmountResp = new RollBackAmountResp();
        TccAmountLog tccAmountLog = this.getAmount(req.getTrackNo(),OrderLogType.FROZEN.getValue());

        TccAmount tccAmount = new TccAmount();
        TccAmount tcc = this.searchAmount(req.getUid());
        tccAmount.setUid(req.getUid());
        Long diff = tcc.getAvailableQuantity() + tccAmountLog.getQuantity();
        Long FrozenDiff = tcc.getFrozenQuantity() - tccAmountLog.getQuantity();
        tccAmount.setFrozenQuantity(FrozenDiff);
        tccAmount.setAvailableQuantity(diff);
        boolean a = amountDao.updateAmount(tccAmount);
        boolean b = amountLogDao.updateAmountLogDao(req.getTrackNo(), OrderLogType.ROLL_BACK.getValue());
        rollBackAmountResp.setStatus(b && a);
        rollBackAmountResp.setTrackNo(req.getTrackNo());
        return rollBackAmountResp;
    }

    @Transactional
    public WriteOffAmountResp writeOffAmount(WriteOffAmountReq req) {
        TccAmountLog tccAmountLog = this.getAmount(req.getTrackNo(),OrderLogType.FROZEN.getValue());
        WriteOffAmountResp writeOffAmountResp = new WriteOffAmountResp();
        TccAmount tccAmount = new TccAmount();
        TccAmount tcc = this.searchAmount(req.getUid());
        tccAmount.setUid(req.getUid());
        Long diff = tcc.getFrozenQuantity() - tccAmountLog.getQuantity();
        tccAmount.setFrozenQuantity(diff);
        tccAmount.setAvailableQuantity(tcc.getAvailableQuantity());
        boolean a = amountDao.updateAmount(tccAmount);
        boolean b = amountLogDao.updateAmountLogDao(req.getTrackNo(), OrderLogType.WRITE_OFF.getValue());
        writeOffAmountResp.setStatus(b && a);
        writeOffAmountResp.setTrackNo(req.getTrackNo());
        return writeOffAmountResp;
    }
    public TccAmount searchAmount(String uid) {
        return amountDao.queryAmount(uid);
    }
    public TccAmountLog getAmount(String trackNo,Integer type) {
        TccAmountLog tccAmountLog = amountLogDao.queryAmountLogDao(trackNo,type);
        if (null == tccAmountLog) {
            throw new TccException("TrackNoNotExits","TrackNo不存在");
        }
        return tccAmountLog;
    }
}
