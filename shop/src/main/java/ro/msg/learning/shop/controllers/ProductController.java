package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.services.product_service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable Integer productId) {
        return productService.getProductById(productId);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @PutMapping("/{productId}")
    public void updateProduct(@PathVariable Integer productId, @RequestBody Product product) {
        productService.updateProduct(productId, product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
    }
}
