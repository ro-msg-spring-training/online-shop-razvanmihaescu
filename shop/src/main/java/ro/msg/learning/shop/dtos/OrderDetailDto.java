package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailDto {

    private Integer productId;

    private Integer quantity;
}
