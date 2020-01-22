package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;

@Component
public class ProductMapper {
    public ProductDto convertToDto(Product product) {
        return ProductDto.builder()
                .categoryDescription(product.getCategory().getDescription())
                .categoryName(product.getCategory().getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .productName(product.getName())
                .price(product.getPrice())
                .weight(product.getWeight())
                .build();
    }

    public Product convertToEntity(ProductDto productDto) {
        return Product.builder()
                .category(null)
                .description(productDto.getDescription())
                .imageUrl(productDto.getImageUrl())
                .name(productDto.getProductName())
                .price(productDto.getPrice())
                .stocks(null)
                .weight(productDto.getWeight())
                .build();
    }
}
