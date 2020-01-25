package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;
import ro.msg.learning.shop.entities.OrderDetail;

@Data
@Builder
public class StockDto {

    private ProductDto product;

    private OrderDetailDto orderDetail;

    private LocationDto location;

    private Integer quantity;
}
