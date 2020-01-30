package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductCategoryDto;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.services.productCategory_service.IProductCategoryService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ProductCategory")
public class ProductCategoryController {

    @Autowired
    private IProductCategoryService productCategoryService;

    @GetMapping
    public List<ProductCategoryDto> getAllProductCategories() {
        return productCategoryService.getProductCategories();
    }

    @GetMapping("/{productCategoryId}")
    public ProductCategoryDto getProductCategoryById(@PathVariable Integer productCategoryId) {
        return productCategoryService.getProductCategoryById(productCategoryId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCategoryDto addProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        return productCategoryService.createProductCategory(productCategoryDto);
    }

    @PutMapping("/{productCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductCategoryDto updateProductCategory(@PathVariable Integer productCategoryId, @RequestBody ProductCategoryDto productCategoryDto) {
        return productCategoryService.updateProductCategory(productCategoryId, productCategoryDto);
    }

    @DeleteMapping("/{productCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductCategory(@PathVariable Integer productCategoryId) {
        productCategoryService.deleteProductCategory(productCategoryId);
    }
}
