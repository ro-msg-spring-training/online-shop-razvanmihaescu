package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Product;

@Repository
public
interface IProductRepository extends JpaRepository<Product, Integer> {

    void deleteProductByProductId(Integer productId);
}
