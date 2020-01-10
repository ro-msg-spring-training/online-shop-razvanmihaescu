package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.services.product_category_service.IProductCategoryService;
import ro.msg.learning.shop.services.product_category_service.ProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/ProductCategory")
public class ProductCategoryController {

    @Autowired
    private IProductCategoryService productCategoryService;

    @GetMapping
    public List<ProductCategory> getAllProductCategorys() {
        return productCategoryService.getProductCategorys();
    }

    @GetMapping("/{productCategoryId}")
    public ProductCategory getProductCategoryById(@PathVariable Integer productCategoryId) {
        return productCategoryService.getProductCategoryById(productCategoryId);
    }

    @PostMapping
    public void addProductCategory(@RequestBody ProductCategory productCategory) {
        productCategoryService.createProductCategory(productCategory);
    }

    @PutMapping("/{productCategoryId}")
    public void updateProductCategory(@PathVariable Integer productCategoryId, @RequestBody ProductCategory productCategory) {
        productCategoryService.updateProductCategory(productCategoryId, productCategory);
    }

    @DeleteMapping("/{productCategoryId}")
    public void deleteProductCategory(@PathVariable Integer productCategoryId) {
        productCategoryService.deleteProductCategory(productCategoryId);
    }
}
