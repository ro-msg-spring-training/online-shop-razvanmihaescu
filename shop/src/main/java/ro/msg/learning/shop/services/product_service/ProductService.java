package ro.msg.learning.shop.services.product_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.ProductCategoryDto;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.mappers.ProductMapper;
import ro.msg.learning.shop.repositories.IProductCategoryRepository;
import ro.msg.learning.shop.repositories.IProductRepository;
import ro.msg.learning.shop.services.productCategory_service.IProductCategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    IProductCategoryRepository productCategoryRepository;

    @Autowired
    private IProductCategoryService productCategoryService;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product newProduct = convertToEntity(productDto);
        ProductCategory productCategory = productCategoryService.getProductCategoryByName(productDto.getCategory());
        if (productCategory == null) {
            productCategoryService.createProductCategory(ProductCategoryDto.builder()
                    .name(productDto.getCategory())
                    .build());
            newProduct.setCategory(productCategoryService.getProductCategoryByName(productDto.getCategory()));
        } else {
            newProduct.setCategory(productCategory);
        }
        return convertToDto(productRepository.save(newProduct));
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        Product newProduct = convertToEntity(productDto);
        ProductCategory productCategory = productCategoryService.getProductCategoryByName(productDto.getCategory());
        if (productCategory == null) {
            productCategoryService.createProductCategory(ProductCategoryDto.builder()
                    .name(productDto.getCategory())
                    .build());
            newProduct.setCategory(productCategoryService.getProductCategoryByName(productDto.getCategory()));
        } else {
            newProduct.setCategory(productCategory);
        }
        newProduct.setId(id);
        Product persistedProduct = productRepository.save(newProduct);
        return convertToDto(persistedProduct);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> posts = productRepository.findAll();
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return convertToDto(productOptional.get());
        } else throw new ProductNotFoundException(productId);
    }


    @Override
    public ProductDto convertToDto(Product product) {
        return productMapper.convertToDto(product);
    }

    @Override
    public Product convertToEntity(ProductDto productDto) {
        return productMapper.convertToEntity(productDto);
    }
}
