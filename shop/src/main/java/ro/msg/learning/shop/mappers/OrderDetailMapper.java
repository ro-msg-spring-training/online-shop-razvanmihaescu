package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.entities.OrderDetail;

@Component
public class OrderDetailMapper {

    public OrderDetailDto convertToDto(OrderDetail orderDetail) {
        return OrderDetailDto.builder()
                .productId(orderDetail.getProductId())
                .quantity(orderDetail.getQuantity())
                .build();
    }

    public OrderDetail convertToEntity(OrderDetailDto orderDetailDto) {
        return OrderDetail.builder()
                .productId(orderDetailDto.getProductId())
                .quantity(orderDetailDto.getQuantity())
                .build();
    }
}
