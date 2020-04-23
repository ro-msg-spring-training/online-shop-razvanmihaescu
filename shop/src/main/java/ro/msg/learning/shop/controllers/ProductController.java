package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.mappers.IProductMapper;
import ro.msg.learning.shop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<ProductDto> getAllProducts() {
        return productService.getProducts().stream().map(IProductMapper.INSTANCE::productToProductDto).collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ProductDto getProductById(@PathVariable Integer productId) {
        return IProductMapper.INSTANCE.productToProductDto(productService.getProductById(productId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return IProductMapper.INSTANCE.productToProductDto(productService.createProduct(productDto));
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct(@PathVariable Integer productId, @RequestBody ProductDto productDto) {
        return IProductMapper.INSTANCE.productToProductDto(productService.updateProduct(productId, productDto));
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);
    }


}
