package ro.msg.learning.shop.services.product_service;

import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;

import java.util.List;

public interface IProductService {
    ProductDto createProduct(ProductDto productDto);

    void updateProduct(Integer id, ProductDto productDto);

    void deleteProduct(Integer id);

    List<Product> getProducts();

    ProductDto getProductById(Integer productId);
}
