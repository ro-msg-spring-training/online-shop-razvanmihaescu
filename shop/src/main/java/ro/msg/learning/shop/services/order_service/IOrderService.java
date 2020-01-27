package ro.msg.learning.shop.services.order_service;

import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.entities.Order;

public interface IOrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderById(Integer id);

    OrderDto convertToDto(Order order);

    Order convertToEntity(OrderDto orderDto);
}
