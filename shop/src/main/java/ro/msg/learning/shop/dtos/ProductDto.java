package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private String productName;

    private String description;

    private Float price;

    private Double weight;

    private String categoryName;

    private String categoryDescription;

    private String imageUrl;

}
