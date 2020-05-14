package ro.msg.learning.shop.configuration.strategies;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.ProductsNotAvailableException;
import ro.msg.learning.shop.mappers.ILocationMapper;
import ro.msg.learning.shop.mappers.IProductMapper;
import ro.msg.learning.shop.services.StockService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Component
@Data
public class MostAbundantStrategy implements IDeliveryStrategy {

    private final StockService stockService;

    @Override
    public List<StockDto> doAlgorithm(List<OrderDetail> orderDetails) {
        List<StockDto> dtoToReturn = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails) {
            List<Stock> currentProductStocks = stockService.getStocksByProductId(orderDetail.getProductId());
            Stock maxStock = currentProductStocks.stream().max(Comparator.comparing(Stock::getQuantity)).orElseThrow(ProductsNotAvailableException::new);
            if (maxStock.getQuantity() < orderDetail.getQuantity())
                throw new ProductsNotAvailableException();

            dtoToReturn.add(StockDto.builder()
                    .locationDto(ILocationMapper.INSTANCE.locationToLocationDto(maxStock.getLocation()))
                    .quantity(orderDetail.getQuantity())
                    .productDto(IProductMapper.INSTANCE.productToProductDto(maxStock.getProduct()))
                    .build());
        }
        return dtoToReturn;
    }
}
