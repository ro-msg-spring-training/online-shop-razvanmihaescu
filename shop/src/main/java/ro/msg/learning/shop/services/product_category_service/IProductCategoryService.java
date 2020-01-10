package ro.msg.learning.shop.services.product_category_service;

import ro.msg.learning.shop.entities.ProductCategory;

import java.util.List;

public interface IProductCategoryService {
    void createProductCategory(ProductCategory productCategory);

    void updateProductCategory(Integer id, ProductCategory productCategory);

    void deleteProductCategory(Integer id);

    List<ProductCategory> getProductCategorys();

    ProductCategory getProductCategoryById(Integer productCategoryId);
}
