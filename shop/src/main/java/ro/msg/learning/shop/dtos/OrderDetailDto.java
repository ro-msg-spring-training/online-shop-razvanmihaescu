package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {

    private Integer productId;

    private Integer quantity;
}
