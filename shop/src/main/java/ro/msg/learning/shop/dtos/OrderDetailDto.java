package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDetailDto {

    private Integer productId;

    private Integer quantity;
}
