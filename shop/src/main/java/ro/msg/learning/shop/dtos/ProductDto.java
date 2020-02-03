package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private Integer id;

    private String name;

    private String description;

    private Float price;

    private Double weight;

    private String category;

    private String imageUrl;
}
