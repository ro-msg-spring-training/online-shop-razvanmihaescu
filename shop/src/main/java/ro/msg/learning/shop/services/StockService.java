package ro.msg.learning.shop.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.StockCsvDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.mappers.IStockMapper;
import ro.msg.learning.shop.repositories.IStockRepository;
import ro.msg.learning.shop.util.MessageConverter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {

    private final IStockRepository stockRepository;
    private final MessageConverter<StockCsvDto> messageConverter;

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public List<Stock> getStocksByLocation(Location location) {
        return stockRepository.findAllByLocation_Id(location.getId());
    }

    public List<Stock> getStocksByProductId(Integer productId) {
        return stockRepository.findStocksByProduct_Id(productId);
    }

    public String exportStockForGivenLocation(Integer locationId) throws JsonProcessingException {
        return messageConverter.getCsvConverter().toCsv(StockCsvDto.class, stockRepository.findAllByLocation_Id(locationId).stream().map(IStockMapper.INSTANCE::stockToStockCsvDto).collect(Collectors.toList()));
    }

    public List<Stock> importStockFromCSV() throws IOException {
        File file = new File("src\\main\\java\\ro\\msg\\learning\\shop\\util\\Stock.csv");
        messageConverter.getCsvConverter().setUploadedFile(file);
        List<StockCsvDto> stockCsvDtoList = messageConverter.getCsvConverter().fromCsv(StockCsvDto.class);
        return stockCsvDtoList.stream().map(IStockMapper.INSTANCE::stockCsvDtoToStock).collect(Collectors.toList());
    }
}