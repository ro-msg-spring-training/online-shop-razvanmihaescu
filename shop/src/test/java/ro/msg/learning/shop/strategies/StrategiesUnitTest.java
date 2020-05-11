package ro.msg.learning.shop.strategies;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.configuration.strategies.MostAbundantStrategy;
import ro.msg.learning.shop.configuration.strategies.SingleLocationStrategy;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.ProductsNotAvailableException;
import ro.msg.learning.shop.services.LocationService;
import ro.msg.learning.shop.services.StockService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class StrategiesUnitTest {

    @InjectMocks
    private MostAbundantStrategy mostAbundantStrategy;

    @InjectMocks
    private SingleLocationStrategy singleLocationStrategy;

    @Mock
    private LocationService locationService;

    @Mock
    private StockService stockService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Product testProduct = Product.builder()
                .name("Product 1")
                .id(1)
                .build();

        List<Location> locationList = new ArrayList<>();
        locationList.add(Location.builder()
                .name("Location 1")
                .id(1)
                .build());

        List<Stock> stockList = new ArrayList<>();
        stockList.add(Stock.builder()
                .location(locationList.get(0))
                .quantity(10)
                .product(testProduct)
                .build());

        Mockito.when(locationService.getAllLocations())
                .thenReturn(locationList);

        Mockito.when(stockService.getStocksByLocation(locationList.get(0)))
                .thenReturn(stockList);

        Mockito.when(stockService.getStocksByProductId(1))
                .thenReturn(stockList);
    }

    @Test
    public void singleLocationStrategyTest() {
        List<OrderDetail> exceptionGeneratingOrderDetailList = new ArrayList<>();
        exceptionGeneratingOrderDetailList.add(OrderDetail.builder()
                .productId(1)
                .quantity(10)
                .build());
        exceptionGeneratingOrderDetailList.add(OrderDetail.builder()
                .productId(2)
                .quantity(20)
                .build());

        assertThatExceptionOfType(ProductsNotAvailableException.class)
                .isThrownBy(() -> singleLocationStrategy.doAlgorithm(exceptionGeneratingOrderDetailList));

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(OrderDetail.builder()
                .productId(1)
                .quantity(10)
                .build());

        List<StockDto> stockDtoList = singleLocationStrategy.doAlgorithm(orderDetailList);

        assertThat(stockDtoList).isNotNull().hasSize(1);
    }

    @Test
    public void mostAbundantStrategyTest() {
        List<OrderDetail> exceptionGeneratingOrderDetailList = new ArrayList<>();
        exceptionGeneratingOrderDetailList.add(OrderDetail.builder()
                .productId(1)
                .quantity(10)
                .build());
        exceptionGeneratingOrderDetailList.add(OrderDetail.builder()
                .productId(2)
                .quantity(20)
                .build());

        assertThatExceptionOfType(ProductsNotAvailableException.class)
                .isThrownBy(() -> mostAbundantStrategy.doAlgorithm(exceptionGeneratingOrderDetailList));

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(OrderDetail.builder()
                .productId(1)
                .quantity(10)
                .build());

        List<StockDto> stockDtoList = mostAbundantStrategy.doAlgorithm(orderDetailList);

        assertThat(stockDtoList).isNotNull().hasSize(1);
    }
}
