package ro.msg.learning.shop.services.product_category_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;

import java.util.List;

@Service
public class ProductCategoryService implements IProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public void createProductCategory(ProductCategory productCategory) {

    }

    @Override
    public void updateProductCategory(Integer id, ProductCategory productCategory) {

    }

    @Override
    public void deleteProductCategory(Integer id) {

    }

    @Override
    public List<ProductCategory> getProductCategorys() {
        return null;
    }

    @Override
    public ProductCategory getProductCategoryById(Integer productCategoryId) {
    return null;
    }
}

