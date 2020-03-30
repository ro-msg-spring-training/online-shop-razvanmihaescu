package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.CartDto;
import ro.msg.learning.shop.entities.Cart;

@Component
public class CartMapper {
    public CartDto convertToDto(Cart cart) {
        return CartDto.builder()
                .productId(cart.getProductId())
                .quantity(cart.getQuantity())
                .build();
    }

    public Cart convertToEntity(CartDto cartDTO) {
        return Cart.builder()
                .productId(cartDTO.getProductId())
                .quantity(cartDTO.getQuantity())
                .build();
    }
}
