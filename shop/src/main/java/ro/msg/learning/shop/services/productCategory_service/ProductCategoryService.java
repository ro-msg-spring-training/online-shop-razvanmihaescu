package ro.msg.learning.shop.services.productCategory_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.ProductCategoryDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.exceptions.ProductCategoryNotFoundException;
import ro.msg.learning.shop.mappers.ProductCategoryMapper;
import ro.msg.learning.shop.repositories.IProductCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService implements IProductCategoryService {

    @Autowired
    private IProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto) {
        ProductCategory newProductCategory = convertToEntity(productCategoryDto);
        return convertToDto(productCategoryRepository.save(newProductCategory));
    }

    @Override
    public ProductCategoryDto updateProductCategory(Integer id, ProductCategoryDto productCategoryDto) {
        ProductCategory newProductCategory = convertToEntity(productCategoryDto);
        newProductCategory.setId(id);
        ProductCategory persistedProduct = productCategoryRepository.save(newProductCategory);
        return convertToDto(persistedProduct);
    }

    @Override
    public void deleteProductCategory(Integer id) {
        productCategoryRepository.deleteById(id);
    }

    @Override
    public List<ProductCategoryDto> getProductCategories() {
        return productCategoryRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public ProductCategoryDto getProductCategoryById(Integer productCategoryId) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(productCategoryId);
        if (productCategoryOptional.isPresent()) {
            return convertToDto(productCategoryOptional.get());
        } else throw new ProductCategoryNotFoundException(productCategoryId);
    }

    @Override
    public ProductCategory getProductCategoryByName(String productCategoryName) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findByNameEquals(productCategoryName);
        return productCategoryOptional.orElse(null);
    }


    @Override
    public ProductCategoryDto convertToDto(ProductCategory productCategory) {
        return productCategoryMapper.convertToDto(productCategory);
    }

    @Override
    public ProductCategory convertToEntity(ProductCategoryDto productCategoryDto) {
        return productCategoryMapper.convertToEntity(productCategoryDto);
    }

}

