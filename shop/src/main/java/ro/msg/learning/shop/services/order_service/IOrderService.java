package ro.msg.learning.shop.services.order_service;

import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.entities.Order;

import java.util.List;

public interface IOrderService {
    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrderById(Integer id);

    OrderDto convertToDto(Order order);

    Order convertToEntity(OrderDto orderDto);

    List<OrderDto> getOrders();

    OrderDto updateOrder(Integer orderId, OrderDto orderDto);

    void deleteOrderById(Integer orderId);
}
