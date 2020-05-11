package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockCsvDto {

    private Integer stockId;

    private Integer quantity;

    private String locationName;

    private String productName;
}
