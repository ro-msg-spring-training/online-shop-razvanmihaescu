package ro.msg.learning.shop.services.orderDetail_service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.repositories.IOrderDetailRepository;

import java.util.List;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    IOrderDetailRepository orderDetailRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void persistOrderDetails(List<OrderDetail> orderDetails) {
        orderDetailRepository.saveAll(orderDetails);
    }

    @Override
    public OrderDetailDto convertToDto(OrderDetail orderDetail) {
        return modelMapper.map(orderDetail, OrderDetailDto.class);
    }

    @Override
    public OrderDetail convertToEntity(OrderDetailDto orderDetailDto) {
        return modelMapper.map(orderDetailDto, OrderDetail.class);
    }
}
