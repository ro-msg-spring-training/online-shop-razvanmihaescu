package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.ProductCategoryDto;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.exceptions.ProductCategoryNotFoundException;
import ro.msg.learning.shop.mappers.IProductCategoryMapper;
import ro.msg.learning.shop.repositories.IProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final IProductCategoryRepository productCategoryRepository;

    public ProductCategory createProductCategory(ProductCategoryDto productCategoryDto) {
        ProductCategory newProductCategory = IProductCategoryMapper.INSTANCE.productCategoryDtoToProductCategory(productCategoryDto);
        return productCategoryRepository.save(newProductCategory);
    }

    public ProductCategory updateProductCategory(Integer id, ProductCategoryDto productCategoryDto) {
        ProductCategory newProductCategory = IProductCategoryMapper.INSTANCE.productCategoryDtoToProductCategory(productCategoryDto);
        newProductCategory.setId(id);
        return productCategoryRepository.save(newProductCategory);
    }

    public void deleteProductCategory(Integer id) {
        productCategoryRepository.deleteById(id);
    }

    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory getProductCategoryById(Integer productCategoryId) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(productCategoryId);
        if (productCategoryOptional.isPresent()) {
            return productCategoryOptional.get();
        } else throw new ProductCategoryNotFoundException(productCategoryId);
    }

    public ProductCategory getProductCategoryByName(String productCategoryName) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findByNameEquals(productCategoryName);
        return productCategoryOptional.orElse(null);
    }
}

