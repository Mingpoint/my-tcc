package org.example.user.dao;

import org.example.user.entity.TccAmount;
import org.example.user.entity.TccAmountLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
        int update = jdbcTemplate.update("insert into t_tcc_amount_log(quantity,exe_time,uid,`type`,track_no) values (?,?,?,?)",
                tLog.getQuantity(), tLog.getExeTime(), tLog.getUid(), tLog.getType(),tLog.getTrackNo());
        return update != 0;
    }
    public TccAmountLog queryAmountLogDao(String trackNo,Integer type) {
        RowMapper<TccAmountLog> rowMapper = new BeanPropertyRowMapper<>();
        TccAmountLog tccAmountLog = jdbcTemplate.queryForObject("select * from  t_tcc_amount_log where track_no=? and `type` =?", rowMapper, trackNo,type);
        return tccAmountLog;
    }

    public boolean updateAmountLogDao(String trackNo,Integer type) {
        int update = jdbcTemplate.update("update t_tcc_amount_log set `type` =?  where track_no=? ", trackNo, type);
        return update != 0;
    }

}
