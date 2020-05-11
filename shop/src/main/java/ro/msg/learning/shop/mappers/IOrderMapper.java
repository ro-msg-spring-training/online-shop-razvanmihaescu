package ro.msg.learning.shop.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.entities.Order;

@Mapper
public interface IOrderMapper {

    IOrderMapper INSTANCE = Mappers.getMapper(IOrderMapper.class);

    @Mapping(target = "orderDetail", source = "orderDetailDtos")
    @Mapping(target = "deliveryAddress", source = "deliveryLocation")
    Order orderDtoToOrder(OrderDto orderDto);

    @InheritInverseConfiguration
    OrderDto orderToOrderDto(Order order);
}
