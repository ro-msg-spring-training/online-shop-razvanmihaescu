package ro.msg.learning.shop.services.cart_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dtos.CartDto;
import ro.msg.learning.shop.entities.Cart;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.mappers.CartMapper;
import ro.msg.learning.shop.repositories.ICartRepository;

@Service
public class CartService implements ICartService{

    @Autowired
    ICartRepository cartRepository;

    @Autowired
    CartMapper cartMapper;

    @Override
    public void deleteById(Integer id, Integer userId) {
        cartRepository.deleteCartByProductIdAndUserId(id, userId);
    }

    @Override
    public CartDto convertToDto(Cart cart) {
        return cartMapper.convertToDto(cart);
    }

    @Override
    public Cart convertToEntity(CartDto cartDto) {
        return cartMapper.convertToEntity(cartDto);
    }

    @Transactional
    @Override
    public void clearUserCart(User user) {
        cartRepository.deleteCartsByUser(user);
    }
}
