package ro.msg.learning.shop.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;

@Component
public class ProductMapper {

    @Autowired
    ProductCategoryMapper productCategoryMapper;

    public ProductDto convertToDto(Product product) {
        return ProductDto.builder()
                .productCategoryDto(productCategoryMapper.convertToDto(product.getCategory()))
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .productName(product.getName())
                .price(product.getPrice())
                .weight(product.getWeight())
                .build();
    }

    public Product convertToEntity(ProductDto productDto) {
        return Product.builder()
                .description(productDto.getDescription())
                .imageUrl(productDto.getImageUrl())
                .name(productDto.getProductName())
                .price(productDto.getPrice())
                .weight(productDto.getWeight())
                .category(productCategoryMapper.convertToEntity(productDto.getProductCategoryDto()))
                .build();
    }
}
