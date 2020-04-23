package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.ProductCategoryDto;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.mappers.IProductMapper;
import ro.msg.learning.shop.repositories.IProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final ProductCategoryService productCategoryService;

    public Product createProduct(ProductDto productDto) {
        Product newProduct = IProductMapper.INSTANCE.productDtoToProduct(productDto);
        ProductCategory productCategory = productCategoryService.getProductCategoryByName(productDto.getCategory().getName());
        if (productCategory == null) {
            productCategoryService.createProductCategory(ProductCategoryDto.builder()
                    .name(productDto.getCategory().getName())
                    .build());
            newProduct.setCategory(productCategoryService.getProductCategoryByName(productDto.getCategory().getName()));
        } else {
            newProduct.setCategory(productCategory);
        }
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Integer id, ProductDto productDto) {
        Product newProduct = IProductMapper.INSTANCE.productDtoToProduct(productDto);
        ProductCategory productCategory = productCategoryService.getProductCategoryByName(productDto.getCategory().getName());
        if (productCategory == null) {
            productCategoryService.createProductCategory(ProductCategoryDto.builder()
                    .name(productDto.getCategory().getName())
                    .build());
            newProduct.setCategory(productCategoryService.getProductCategoryByName(productDto.getCategory().getName()));
        } else {
            newProduct.setCategory(productCategory);
        }
        newProduct.setId(id);
        return productRepository.save(newProduct);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else throw new ProductNotFoundException(productId);
    }
}
