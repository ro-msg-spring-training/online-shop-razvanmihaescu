package ro.msg.learning.shop.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Order;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;


    public OrderDto convertToDto(Order order) {
        return OrderDto.builder()
                .deliveryLocation(addressMapper.convertToDto(order.getDeliveryLocation().getAddress()))
                .orderDetailDtos(order.getOrderDetail().stream().map(a -> orderDetailMapper.convertToDto(a)).collect(Collectors.toList()))
                .build();
    }

    public Order convertToEntity(OrderDto orderDto) {
        Location location = Location.builder()
                .address(addressMapper.convertToEntity(orderDto.getDeliveryLocation()))
                .build();

        return Order.builder()
                .deliveryLocation(location)
                .orderDetail(orderDto.getOrderDetailDtos().stream().map(a -> orderDetailMapper.convertToEntity(a)).collect(Collectors.toList()))
                .build();
    }
}
