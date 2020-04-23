package ro.msg.learning.shop.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dtos.CartDto;
import ro.msg.learning.shop.entities.Cart;

@Mapper
public interface ICartMapper {

    ICartMapper INSTANCE = Mappers.getMapper(ICartMapper.class);

    Cart cartDtoToCart(CartDto cartDto);

    @InheritInverseConfiguration
    CartDto cartToCartDto(Cart cart);
}
