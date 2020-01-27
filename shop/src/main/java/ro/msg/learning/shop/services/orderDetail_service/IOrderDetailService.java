package ro.msg.learning.shop.services.orderDetail_service;

import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.entities.OrderDetail;

public interface IOrderDetailService {

    OrderDetailDto convertToDto(OrderDetail orderDetail);

    OrderDetail convertToEntity(OrderDetailDto orderDetailDto);
}
