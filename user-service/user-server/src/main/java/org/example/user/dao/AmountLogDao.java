package org.example.user.dao;

import org.example.user.entity.TccAmountLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/24 17:48
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.user.dao
 */
@Component
public class AmountLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public boolean insertAmountLogDao(TccAmountLog tLog) {
        int update = jdbcTemplate.update("insert into t_tcc_amount_log(quantity,exe_time,uid,`type`) values (?,?,?,?)",
                tLog.getQuantity(), tLog.getExeTime(), tLog.getUid(), tLog.getType());
        return update != 0;
    }
}
