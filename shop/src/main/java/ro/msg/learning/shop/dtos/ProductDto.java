package ro.msg.learning.shop.dtos;

import lombok.Data;

@Data
public class ProductDto {
    private String name;

    private String description;

    private Float price;

    private Double weight;

    private String categoryName;

    private String categoryDescription;

    private String imageUrl;

}
