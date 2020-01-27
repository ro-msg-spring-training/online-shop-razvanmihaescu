package ro.msg.learning.shop.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private String productName;

    private String description;

    private Float price;

    private Double weight;

    @JsonAlias("category")
    private ProductCategoryDto productCategoryDto;

    private String imageUrl;

}
