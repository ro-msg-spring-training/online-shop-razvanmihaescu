package ro.msg.learning.shop.dtos;

import lombok.Data;

@Data
public class StockCsvDto {

    private Integer stockId;

    private Integer quantity;

    private String locationName;

    private String productName;


}
