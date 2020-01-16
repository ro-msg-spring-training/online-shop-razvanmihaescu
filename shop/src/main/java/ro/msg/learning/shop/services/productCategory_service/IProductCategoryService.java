package ro.msg.learning.shop.services.productCategory_service;

import ro.msg.learning.shop.entities.ProductCategory;

import java.util.List;

public interface IProductCategoryService {
    ProductCategory createProductCategory(ProductCategory productCategory);

    void updateProductCategory(Integer id, ProductCategory productCategory);

    void deleteProductCategory(Integer id);

    List<ProductCategory> getProductCategories();

    ProductCategory getProductCategoryById(Integer productCategoryId);

    ProductCategory getProductCategoryByName(String productCategoryName);
}
