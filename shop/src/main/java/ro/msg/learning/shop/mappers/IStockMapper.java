package ro.msg.learning.shop.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dtos.StockCsvDto;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.Stock;

@Mapper
public interface IStockMapper {

    IStockMapper INSTANCE = Mappers.getMapper(IStockMapper.class);

    Stock stockDtoToStock(StockDto stockDto);

    @InheritInverseConfiguration
    StockDto stockToStockDto(Stock stock);


    @Mapping(target = "product.name", source = "productName")
    @Mapping(target = "location.name", source = "locationName")
    @Mapping(target = "id", source = "stockId")
    Stock stockCsvDtoToStock(StockCsvDto stockCsvDto);

    @InheritInverseConfiguration
    StockCsvDto stockToStockCsvDto(Stock stock);
}
