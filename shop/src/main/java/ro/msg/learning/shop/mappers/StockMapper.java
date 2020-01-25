package ro.msg.learning.shop.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.Stock;

@Component
public class StockMapper {

    @Autowired
    LocationMapper locationMapper;

    @Autowired
    ProductMapper productMapper;

    public StockDto convertToDto(Stock stock) {
        return StockDto.builder()
                .location(locationMapper.convertToDto(stock.getLocation()))
                .product(productMapper.convertToDto(stock.getProduct()))
                .quantity(stock.getQuantity())
                .build();
    }

    public Stock convertToEntity(StockDto stockDto) {
        return Stock.builder()
                .location(locationMapper.convertToEntity(stockDto.getLocation()))
                .product(productMapper.convertToEntity(stockDto.getProduct()))
                .quantity(stockDto.getQuantity())
                .build();
    }
}
