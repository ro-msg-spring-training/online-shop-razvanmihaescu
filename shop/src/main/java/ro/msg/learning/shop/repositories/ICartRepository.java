package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entities.Cart;
import ro.msg.learning.shop.entities.User;

public interface ICartRepository extends JpaRepository<Cart, Integer> {
    void deleteCartByProductIdAndUserId(Integer id, Integer userId);
    void deleteCartsByUser(User user);
}
