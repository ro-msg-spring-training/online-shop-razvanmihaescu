package ro.msg.learning.shop.configuration.strategies;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ro.msg.learning.shop.dtos.MapQuestPayload;
import ro.msg.learning.shop.dtos.MapQuestResponse;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.mappers.ILocationMapper;
import ro.msg.learning.shop.mappers.IProductMapper;
import ro.msg.learning.shop.services.LocationService;
import ro.msg.learning.shop.services.StockService;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Data
public class GreedyStrategy implements IDeliveryStrategy {

    static final String KEY = "GJ1u4F2qvv1KUBWRotWdrTNUskDMUPsm";
    static final String URL = "http://www.mapquestapi.com/directions/v2/routematrix?key=" + KEY;
    private final StockService stockService;
    private final LocationService locationService;

    @Override
    public List<StockDto> doAlgorithm(List<OrderDetail> orderDetails) {
        RestTemplate restTemplate = new RestTemplate();
        List<Location> possibleLocationsForOrder = new ArrayList<>();

        for (Location location : locationService.getAllLocations()) {
            List<Stock> stocks = stockService.getStocksByLocation(location);
            if (gotAtLeastAProductInLocationOrNot(stocks, orderDetails)) {
                possibleLocationsForOrder.add(location);
            }
        }

        Address deliveryAddress = orderDetails.get(0).getOrder().getDeliveryAddress();

        List<String> locations = new ArrayList<>();
        locations.add(deliveryAddress.getCity() + ", " + deliveryAddress.getCountry());
        possibleLocationsForOrder.forEach(possibleLocation -> locations.add(possibleLocation.getAddress().getCity() + ", " + possibleLocation.getAddress().getCountry()));

        MapQuestResponse mapQuestResponse = restTemplate.postForObject(URL, MapQuestPayload.builder()
                .locations(locations)
                .manyToOne(true)
                .build(), MapQuestResponse.class);

        Map<Integer, Double> locationAndDistanceMap = new TreeMap<>();

        for (int i = 0; i < possibleLocationsForOrder.size(); i++) {
            locationAndDistanceMap.put(i, mapQuestResponse.getDistance().get(i + 1));
        }

        Map<Integer, Double> sortedMap = locationAndDistanceMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        List<StockDto> dtoToReturn = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails
        ) {
            for (Map.Entry<Integer, Double> pair : sortedMap.entrySet()) {
                Stock stock = stockService.getStocksByLocation(possibleLocationsForOrder.get(pair.getKey())).stream()
                        .filter(element -> element.getProduct().getId().equals(orderDetail.getProductId())).findFirst().orElse(null);
                if (stock != null && stock.getQuantity() >= orderDetail.getQuantity()) {
                    dtoToReturn.add(StockDto.builder()
                            .locationDto(ILocationMapper.INSTANCE.locationToLocationDto(stock.getLocation()))
                            .quantity(orderDetail.getQuantity())
                            .productDto(IProductMapper.INSTANCE.productToProductDto(stock.getProduct()))
                            .build());
                    break;
                }
            }
        }

        return dtoToReturn;
    }

    public boolean gotAtLeastAProductInLocationOrNot(List<Stock> locationStock, List<OrderDetail> orderDetails) {
        for (OrderDetail order : orderDetails) {
            Stock stock = locationStock.stream().filter(element -> element.getProduct().getId().equals(order.getProductId())).findFirst().orElse(null);
            if (stock != null && stock.getQuantity() >= order.getQuantity())
                return true;
        }
        return false;
    }
}
