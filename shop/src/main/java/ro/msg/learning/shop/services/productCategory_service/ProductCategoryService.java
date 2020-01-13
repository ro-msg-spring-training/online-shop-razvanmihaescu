package ro.msg.learning.shop.services.productCategory_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService implements IProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
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

    @Override
    public ProductCategory getProductCategoryByName(String productCategoryName) {
        Optional<ProductCategory> categoryOptional = productCategoryRepository.findByNameEquals(productCategoryName);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
            //TODO
            return null;
        }
    }

    @Override
    public ProductCategory save(ProductCategory newCategory) {
        return productCategoryRepository.save(newCategory);
    }

}

