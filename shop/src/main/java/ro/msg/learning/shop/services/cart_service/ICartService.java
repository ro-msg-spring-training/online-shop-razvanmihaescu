package ro.msg.learning.shop.services.cart_service;

import ro.msg.learning.shop.dtos.CartDto;
import ro.msg.learning.shop.entities.Cart;
import ro.msg.learning.shop.entities.User;

public interface ICartService {
    void deleteById(Integer id, Integer userId);

    CartDto convertToDto(Cart cart);

    Cart convertToEntity(CartDto cartDto);

    void clearUserCart(User user);
}
