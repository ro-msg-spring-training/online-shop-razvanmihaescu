package ro.msg.learning.shop.services.orderDetail_service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.mappers.OrderDetailMapper;
import ro.msg.learning.shop.repositories.IOrderDetailRepository;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    IOrderDetailRepository orderDetailRepository;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    public void persistOrderDetails(List<OrderDetail> orderDetails) {
        orderDetailRepository.saveAll(orderDetails);
    }

    @Override
    public OrderDetailDto convertToDto(OrderDetail orderDetail) {
        return orderDetailMapper.convertToDto(orderDetail);
    }

    @Override
    public OrderDetail convertToEntity(OrderDetailDto orderDetailDto) {
        return orderDetailMapper.convertToEntity(orderDetailDto);
    }
}
