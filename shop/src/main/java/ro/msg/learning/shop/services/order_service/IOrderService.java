package ro.msg.learning.shop.services.order_service;

import ro.msg.learning.shop.dtos.OrderDto;

public interface IOrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderById(Integer id);
}
