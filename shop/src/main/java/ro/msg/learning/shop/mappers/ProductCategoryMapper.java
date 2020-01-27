package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductCategoryDto;
import ro.msg.learning.shop.entities.ProductCategory;

@Component
public class ProductCategoryMapper {

    public ProductCategoryDto convertToDto(ProductCategory productCategory) {
        return ProductCategoryDto.builder()
                .name(productCategory.getName())
                .description(productCategory.getDescription())
                .build();
    }

    public ProductCategory convertToEntity(ProductCategoryDto productCategoryDto) {
        return ProductCategory.builder()
                .name(productCategoryDto.getName())
                .description(productCategoryDto.getDescription())
                .build();
    }
}
