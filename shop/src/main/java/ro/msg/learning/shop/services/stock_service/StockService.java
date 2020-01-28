package ro.msg.learning.shop.services.stock_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.repositories.IStockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockService implements IStockService {

    @Autowired
    IStockRepository stockRepository;

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public List<Stock> getStocksByLocation(Location location) {
        Optional<List<Stock>> stockOptional = stockRepository.findAllByLocation(location);
        return stockOptional.orElse(null);
    }

    @Override
    public List<Stock> getStocksByProductId(Integer productId) {
        return stockRepository.findStocksByProduct_Id(productId);
    }
}
