package ro.msg.learning.shop.services.product_service;

import ro.msg.learning.shop.entities.Product;

import java.util.List;

public interface IProductService {
    void createProduct(Product product);

    void updateProduct(Integer id, Product product);

    void deleteProduct(Integer id);

    List<Product> getProducts();

    Product getProductById(Integer productId);
}
