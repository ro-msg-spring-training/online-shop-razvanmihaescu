package ro.msg.learning.shop.services.order_service;

import ro.msg.learning.shop.entities.Orders;

public interface IOrderService {
    Orders createOrder(Orders order);

    Orders getOrderById(Integer id);
}
