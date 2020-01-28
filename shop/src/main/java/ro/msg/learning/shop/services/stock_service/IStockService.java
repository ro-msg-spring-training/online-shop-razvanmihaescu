package ro.msg.learning.shop.services.stock_service;

import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.Stock;

import java.util.List;

public interface IStockService {
    List<Stock> getAllStock();

    List<Stock> getStocksByLocation(Location location);

     List<Stock> getStocksByProductId(Integer productId);
}
