package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.services.productCategory_service.IProductCategoryService;

import java.util.List;

@RestController
@RequestMapping("/ProductCategory")
public class ProductCategoryController {

    @Autowired
    private IProductCategoryService productCategoryService;

    @GetMapping
    public List<ProductCategory> getAllProductCategorys() {
        return productCategoryService.getProductCategories();
    }

    @GetMapping("/{productCategoryId}")
    public ProductCategory getProductCategoryById(@PathVariable Integer productCategoryId) {
        return productCategoryService.getProductCategoryById(productCategoryId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
