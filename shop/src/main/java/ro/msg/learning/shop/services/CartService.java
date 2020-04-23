package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.repositories.ICartRepository;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ICartRepository cartRepository;

    public void deleteById(Integer id, Integer userId) {
        cartRepository.deleteCartByProductIdAndUserId(id, userId);
    }

    @Transactional
    public void clearUserCart(User user) {
        cartRepository.deleteCartsByUser(user);
    }
}
