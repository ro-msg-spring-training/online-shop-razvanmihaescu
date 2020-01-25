package ro.msg.learning.shop.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.mappers.LocationMapper;
import ro.msg.learning.shop.mappers.OrderDetailMapper;
import ro.msg.learning.shop.services.location_service.ILocationService;
import ro.msg.learning.shop.services.stock_service.IStockService;

import java.util.List;

@Data
public class SingleLocationStrategy implements IDeliveryStrategy {
    @Autowired
    IStockService stockService;

    @Autowired
    ILocationService locationService;

    @Autowired
    LocationMapper locationMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;


    Location locationToReturn;
    Boolean response;
    List<StockDto> dtoToReturn = null;

    @Override
    public List<StockDto> doAlgorithm(List<OrderDetail> orderDetails) {

        locationToReturn = null;
        locationService.getAllLocations().forEach(location -> {
            List<Stock> stocks = stockService.getStocksByLocation(location);
            if (gotAllProductsInLocation(stocks, orderDetails))
                this.locationToReturn = location;
        });

        return dtoToReturn;
    }

    public boolean gotAllProductsInLocation(List<Stock> stocks, List<OrderDetail> orderDetails) {
        orderDetails.forEach(order -> {
            Stock stockk = stocks.stream().filter(stock -> stock.getProduct().getProductId().equals(order.getProductId())).findFirst().orElse(null);
            if (stockk == null || stockk.getQuantity() < order.getQuantity()) this.response = false;
            else {
                dtoToReturn.add(StockDto.builder()
                        .location(locationMapper.convertToDto(stockk.getLocation()))
                        .orderDetail(orderDetailMapper.convertToDto(order))
                        .build());
            }
        });
        return this.response;
    }
}
