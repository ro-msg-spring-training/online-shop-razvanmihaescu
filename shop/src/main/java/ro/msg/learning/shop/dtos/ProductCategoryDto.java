package ro.msg.learning.shop.dtos;

import lombok.Data;
import ro.msg.learning.shop.entities.Product;

import java.util.List;

@Data
public class ProductCategoryDto {
    private String name;

    private String description;

    private List<Product> products;

}
