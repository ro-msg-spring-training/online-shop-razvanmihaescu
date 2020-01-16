package ro.msg.learning.shop.services.productCategory_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.repositories.IProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService implements IProductCategoryService {

    @Autowired
    private IProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public void updateProductCategory(Integer id, ProductCategory productCategory) {

    }

    @Override
    public void deleteProductCategory(Integer id) {
    productCategoryRepository.deleteById(id);
    }

    @Override
    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryById(Integer productCategoryId) {
        return productCategoryRepository.getOne(productCategoryId);
    }

    @Override
    public ProductCategory getProductCategoryByName(String productCategoryName) {
        Optional<ProductCategory> categoryOptional = productCategoryRepository.findByNameEquals(productCategoryName);
        return categoryOptional.orElse(null);
    }
}

