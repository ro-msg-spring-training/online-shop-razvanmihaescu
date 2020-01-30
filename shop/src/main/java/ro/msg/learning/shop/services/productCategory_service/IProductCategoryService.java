package ro.msg.learning.shop.services.productCategory_service;

import ro.msg.learning.shop.dtos.ProductCategoryDto;
import ro.msg.learning.shop.entities.ProductCategory;

import java.util.List;

public interface IProductCategoryService {
    ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto);

    ProductCategoryDto updateProductCategory(Integer id, ProductCategoryDto productCategoryDto);

    void deleteProductCategory(Integer id);

    List<ProductCategoryDto> getProductCategories();

    ProductCategoryDto getProductCategoryById(Integer productCategoryId);

    ProductCategory getProductCategoryByName(String productCategoryName);

    ProductCategoryDto convertToDto(ProductCategory productCategory);

    ProductCategory convertToEntity(ProductCategoryDto productCategoryDto);
}
