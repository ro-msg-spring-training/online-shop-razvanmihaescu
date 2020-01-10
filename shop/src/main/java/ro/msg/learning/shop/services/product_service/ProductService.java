package ro.msg.learning.shop.services.product_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {

    }

    @Override
    public void updateProduct(Integer id, Product product) {

    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteProductByProductId(id);
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.findProductByProductId(productId);
    }
}
