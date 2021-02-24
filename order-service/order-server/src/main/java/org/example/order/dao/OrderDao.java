package org.example.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/24 16:33
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.order.dao
 */
@Component
public class OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public boolean insertOrder (Integer buyQuantity, String uid, Double payAmount,String orderNo) {
        int update = jdbcTemplate.update("insert into base_order(buy_quantity,order_no,uid,pay_amount,order_status,is_deleted)" +
                "values(?,?,?,?,?,?)", buyQuantity, orderNo, uid, payAmount, 10, 0);
        return update != 0;
    }
}
