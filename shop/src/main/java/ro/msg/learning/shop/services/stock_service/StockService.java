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
        Optional<List<Stock>> stockOptional = stockRepository.getAllByLocation(location);
        return stockOptional.orElse(null);
    }

}
