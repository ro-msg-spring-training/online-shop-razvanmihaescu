package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductCategoryDto {
    private String name;

    private String description;
}
