package org.example.order.service;

import org.example.order.dao.OrderDao;
import org.example.order.dao.OrderItemDao;
import org.example.order.entity.OrderItem;
import org.example.order.proto.CreateOrderReq;
import org.example.order.proto.CreateOrderResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：min lee
 * @date ：Created in 2021/2/24 16:32
 * @description：
 * @modified By：
 * @version: 0.1
 * @package： org.example.order.service
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Transactional
    public CreateOrderResp createOrder (CreateOrderReq req) {
        boolean a = orderDao.insertOrder(req.getBuyQuantity(), req.getUid(), req.getPayAmount(), req.getOrderNo());
        OrderItem orderItem = new OrderItem();
        BeanUtils.copyProperties(req.getItem(),orderItem);
        boolean b = orderItemDao.insertOrderItem(orderItem);
        CreateOrderResp buyResp = new CreateOrderResp();
        buyResp.setStatus(a && b);
        return buyResp;
    }
}
