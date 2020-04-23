package ro.msg.learning.shop.configuration.strategies;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.ProductsNotAvailableException;
import ro.msg.learning.shop.mappers.ILocationMapper;
import ro.msg.learning.shop.mappers.IProductMapper;
import ro.msg.learning.shop.services.LocationService;
import ro.msg.learning.shop.services.StockService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
@Data
public class SingleLocationStrategy implements IDeliveryStrategy {

    private final StockService stockService;
    private final LocationService locationService;

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
            Stock stock = locationStock.stream().filter(element -> element.getProduct().getId().equals(order.getProductId())).findFirst().orElse(null);
            if (stock == null || stock.getQuantity() < order.getQuantity()) return false;
            else {
                StockDto element = StockDto.builder()
                        .locationDto(ILocationMapper.INSTANCE.locationToLocationDto(stock.getLocation()))
                        .quantity(order.getQuantity())
                        .productDto(IProductMapper.INSTANCE.productToProductDto(stock.getProduct()))
                        .build();
                dtoToReturn.add(element);
            }
        }
        return true;
    }
}
