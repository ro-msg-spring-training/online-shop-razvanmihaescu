package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Orders;
import ro.msg.learning.shop.entities.ProductCategory;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Integer> {

    Orders getById(Integer orderId);
}
