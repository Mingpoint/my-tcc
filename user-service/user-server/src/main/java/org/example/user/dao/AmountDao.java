package org.example.user.dao;

import org.example.user.entity.TccAmount;
import org.example.user.entity.TccAmountLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/24 17:47
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.user.dao
 */
@Component
public class AmountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public boolean insertAmount(TccAmount ta) {
        int update = jdbcTemplate.update("insert into t_tcc_amount(available_quantity,frozen_quantity,uid,) values (?,?,?)",
                ta.getAvailableQuantity(), ta.getFrozenQuantity(), ta.getUid());
        return update != 0;
    }
    public boolean updateAmount(TccAmount ta) {
        int update = jdbcTemplate.update("update t_tcc_amount set available_quantity=?,frozen_quantity=? where uid=?",
                ta.getAvailableQuantity(), ta.getFrozenQuantity(), ta.getUid());
        return update != 0;
    }
    public TccAmount queryAmount(String uid) {
        RowMapper<TccAmount> rowMapper = new BeanPropertyRowMapper<>();
        TccAmount query = jdbcTemplate.queryForObject("select * from t_tcc_amount where uid=?", rowMapper,uid);
        return query;
    }
}
