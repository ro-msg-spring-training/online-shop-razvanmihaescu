package ro.msg.learning.shop.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.ProductsNotAvailableException;
import ro.msg.learning.shop.mappers.OrderDetailMapper;
import ro.msg.learning.shop.mappers.ProductMapper;
import ro.msg.learning.shop.services.location_service.ILocationService;
import ro.msg.learning.shop.services.stock_service.IStockService;

import java.util.ArrayList;
import java.util.List;

@Data
public class SingleLocationStrategy implements IDeliveryStrategy {
    @Autowired
    IStockService stockService;

    @Autowired
    ILocationService locationService;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    ProductMapper productMapper;

    List<StockDto> dtoToReturn = new ArrayList<>();

    @Override
    public List<StockDto> doAlgorithm(List<OrderDetail> orderDetails) {
        boolean foundProducts = false;
        for (Location location : locationService.getAllLocations()) {
            List<Stock> stocks = stockService.getStocksByLocation(location);
            dtoToReturn.clear();
            if (gotAllProductsInLocation(stocks, orderDetails)) {
                foundProducts = true;
                break;
            }

        }
        if (foundProducts)
            return dtoToReturn;
        else
            throw new ProductsNotAvailableException();
    }

    public boolean gotAllProductsInLocation(List<Stock> locationStock, List<OrderDetail> orderDetails) {
        for (OrderDetail order : orderDetails) {
            Stock stock = locationStock.stream().filter(element -> element.getProduct().getProductId().equals(order.getProductId())).findFirst().orElse(null);
            if (stock == null || stock.getQuantity() < order.getQuantity()) return false;
            else {
                StockDto element = StockDto.builder()
                        .locationDto(locationService.convertToDto(stock.getLocation()))
                        .quantity(order.getQuantity())
                        .productDto(productMapper.convertToDto(stock.getProduct()))
                        .build();
                dtoToReturn.add(element);
            }
        }
        return true;
    }
}
