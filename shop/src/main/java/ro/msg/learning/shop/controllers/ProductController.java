package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.services.product_service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ProductDto getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable Integer productId, @RequestBody ProductDto productDto) {
        productService.updateProduct(productId, productDto);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
    }


}
