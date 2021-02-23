package org.example.goods.dao;

import org.example.goods.entity.GoodsStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/23 17:00
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.goods.dao
 */
@Component
public class GoodsStockDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public GoodsStock queryGoodsStore (String productId) {
        RowMapper<GoodsStock> rowMapper = new BeanPropertyRowMapper<>();
        return jdbcTemplate.queryForObject("select * from  product_stock where product_id = ?",rowMapper,productId);
    }
    public boolean updateFrozenGoodsStore (String productId,Integer stockQuantity,Integer frozenQuantity) {
        int i = jdbcTemplate.update("update product_stock set stock_quantity = ?,frozen_quantity = ? where product_id = ?",stockQuantity,frozenQuantity,productId);
        return i == 0;
    }
    public boolean updateWriteOffGoodsStore (String productId,Integer soldQuantity,Integer frozenQuantity) {
        int i = jdbcTemplate.update("update product_stock set sold_quantity = ?,frozen_quantity = ? where product_id = ?",soldQuantity,frozenQuantity,productId);
        return i == 0;
    }
    public boolean updateRollBackGoodsStore (String productId,Integer stockQuantity,Integer soldQuantity,Integer frozenQuantity) {
        int i = jdbcTemplate.update("update product_stock set stock_quantity = ?,sold_quantity = ?,frozen_quantity = ? where product_id = ?",stockQuantity,soldQuantity,frozenQuantity,productId);
        return i == 0;
    }
}
