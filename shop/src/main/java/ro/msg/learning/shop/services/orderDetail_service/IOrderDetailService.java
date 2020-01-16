package ro.msg.learning.shop.services.orderDetail_service;

import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.entities.OrderDetail;

import java.util.List;

public interface IOrderDetailService {

    void persistOrderDetails(List<OrderDetail> orderDetails);

    OrderDetailDto convertToDto(OrderDetail orderDetail);

    OrderDetail convertToEntity(OrderDetailDto orderDetailDto);
}
