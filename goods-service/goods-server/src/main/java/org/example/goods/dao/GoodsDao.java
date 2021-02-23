package org.example.goods.dao;

import org.example.goods.entity.GoodsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 16:40
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.goods.dao
 */
@Component
public class GoodsDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public GoodsInfo queryGoodsInfo (String productId) {
        RowMapper<GoodsInfo> rowMapper = new BeanPropertyRowMapper<>();
        return jdbcTemplate.queryForObject("select * from product_info where product_id = ?", rowMapper, productId);
    }
}
