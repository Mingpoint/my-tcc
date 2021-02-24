package org.example.order.dao;

import org.example.order.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/24 16:58
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.order.dao
 */
@Component
public class OrderItemDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public boolean insertOrderItem (OrderItem item) {
        int update = jdbcTemplate.update("insert into order_item(buy_quantity,order_no,product_id,product_name,product_price,product_image)" +
                "values(?,?,?,?,?,?)",item.getBuyQuantity(),item.getOrderNo(),item.getProductId(),item.getProductName(),item.getProductPrice(),item.getProductImage());
        return update != 0;
    }
}
