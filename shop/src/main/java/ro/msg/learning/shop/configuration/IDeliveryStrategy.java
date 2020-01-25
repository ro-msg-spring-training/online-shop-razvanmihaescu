package ro.msg.learning.shop.configuration;

import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.OrderDetail;

import java.util.List;

public interface IDeliveryStrategy {
    List<StockDto> doAlgorithm(List<OrderDetail> orderDetails);
}
