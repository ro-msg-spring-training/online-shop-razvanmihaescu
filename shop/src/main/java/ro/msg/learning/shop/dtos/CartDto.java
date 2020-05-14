package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartDto {

    private Integer productId;

    private Integer quantity;
}
