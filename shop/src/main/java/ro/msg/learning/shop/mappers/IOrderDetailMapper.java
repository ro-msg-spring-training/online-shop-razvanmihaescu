package ro.msg.learning.shop.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.entities.OrderDetail;

@Mapper
public interface IOrderDetailMapper {

    IOrderDetailMapper INSTANCE = Mappers.getMapper(IOrderDetailMapper.class);

    OrderDetail orderDetailDtoToOrderDetail(OrderDetailDto orderDetailDto);

    @InheritInverseConfiguration
    OrderDetailDto orderDetailToOrderDetailDto(OrderDetail orderDetail);
}
