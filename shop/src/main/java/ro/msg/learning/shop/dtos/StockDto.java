package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDto {

    private LocationDto locationDto;

    private ProductDto productDto;

    private Integer quantity;
}
