package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.services.stock_service.IStockService;

import java.util.List;

public class MostAbundantStrategy implements IDeliveryStrategy {

    @Autowired
    IStockService stockService;

    @Override
    public List<StockDto> doAlgorithm(List<OrderDetail> orderDetails) {
        List<StockDto> dtoToReturn = null;
        Location location = null;
        stockService.getAllStock().forEach(a -> {

        });
        return dtoToReturn;
    }
}
