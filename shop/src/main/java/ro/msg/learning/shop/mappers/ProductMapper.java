package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;

@Component
public class ProductMapper {

    public ProductDto convertToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .name(product.getName())
                .price(product.getPrice())
                .weight(product.getWeight())
                .category(product.getCategory().getName())
                .build();
    }

    public Product convertToEntity(ProductDto productDto) {
        return Product.builder()
                .description(productDto.getDescription())
                .imageUrl(productDto.getImageUrl())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .weight(productDto.getWeight())
                .build();
    }
}
