package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductCategoryDto;
import ro.msg.learning.shop.mappers.IProductCategoryMapper;
import ro.msg.learning.shop.services.ProductCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ProductCategory")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    public List<ProductCategoryDto> getAllProductCategories() {
        return productCategoryService.getProductCategories().stream().map(IProductCategoryMapper.INSTANCE::productCategoryToProductCategoryDto).collect(Collectors.toList());
    }

    @GetMapping("/{productCategoryId}")
    public ProductCategoryDto getProductCategoryById(@PathVariable Integer productCategoryId) {
        return IProductCategoryMapper.INSTANCE.productCategoryToProductCategoryDto(productCategoryService.getProductCategoryById(productCategoryId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCategoryDto addProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        return IProductCategoryMapper.INSTANCE.productCategoryToProductCategoryDto(productCategoryService.createProductCategory(productCategoryDto));
    }

    @PutMapping("/{productCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductCategoryDto updateProductCategory(@PathVariable Integer productCategoryId, @RequestBody ProductCategoryDto productCategoryDto) {
        return IProductCategoryMapper.INSTANCE.productCategoryToProductCategoryDto(productCategoryService.updateProductCategory(productCategoryId, productCategoryDto));
    }

    @DeleteMapping("/{productCategoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductCategory(@PathVariable Integer productCategoryId) {
        productCategoryService.deleteProductCategory(productCategoryId);
    }
}
