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
                .locationDto(locationMapper.convertToDto(stock.getLocation()))
                .quantity(stock.getQuantity())
                .productDto(productMapper.convertToDto(stock.getProduct()))
                .build();
    }

    public Stock convertToEntity(StockDto stockDto) {
        return Stock.builder()
                .location(locationMapper.convertToEntity(stockDto.getLocationDto()))
                .quantity(stockDto.getQuantity())
                .product(productMapper.convertToEntity(stockDto.getProductDto()))
                .build();
    }
}
