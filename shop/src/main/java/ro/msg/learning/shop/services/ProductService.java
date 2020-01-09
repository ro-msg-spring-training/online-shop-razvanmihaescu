package ro.msg.learning.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.List;

interface IProductService {
    public void createProduct(Product product);

    public void updateProduct(String id, Product product);

    public void deleteProduct(String id);

    public List<Product> getProducts();
}

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(Product product) {

    }

    @Override
    public void updateProduct(String id, Product product) {

    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public List<Product> getProducts() {
        return null;
    }
}
