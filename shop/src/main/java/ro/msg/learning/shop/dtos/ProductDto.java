package ro.msg.learning.shop.dtos;

import lombok.Data;
import ro.msg.learning.shop.entities.Stock;

import java.util.List;

@Data
public class ProductDto {
    private Integer productId;

    private String name;

    private String description;

    private Float price;

    private Double weight;

    private ProductCategoryDto category;

    private String imageUrl;

    private List<Stock> stocks;

}
