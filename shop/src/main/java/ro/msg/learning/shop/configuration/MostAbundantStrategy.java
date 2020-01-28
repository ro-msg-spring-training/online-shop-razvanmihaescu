package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.ProductsNotAvailableException;
import ro.msg.learning.shop.services.location_service.ILocationService;
import ro.msg.learning.shop.services.product_service.IProductService;
import ro.msg.learning.shop.services.stock_service.IStockService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MostAbundantStrategy implements IDeliveryStrategy {

    @Autowired
    IStockService stockService;

    @Autowired
    IProductService productService;

    @Autowired
    ILocationService locationService;

    @Override
    public List<StockDto> doAlgorithm(List<OrderDetail> orderDetails) {
        List<StockDto> dtoToReturn = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            List<Stock> currentProductStocks = stockService.getStocksByProductId(orderDetail.getProductId());
            Stock maxStock = currentProductStocks.stream().max(Comparator.comparing(Stock::getQuantity)).orElseThrow(ProductsNotAvailableException::new);
            StockDto element = StockDto.builder()
                    .locationDto(locationService.convertToDto(maxStock.getLocation()))
                    .quantity(orderDetail.getQuantity())
                    .productDto(productService.convertToDto(maxStock.getProduct()))
                    .build();
            dtoToReturn.add(element);
        }
        return dtoToReturn;
    }
}
