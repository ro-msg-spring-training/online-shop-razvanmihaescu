package ro.msg.learning.shop.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.Stock;

@Mapper
public interface IStockMapper {

    IStockMapper INSTANCE = Mappers.getMapper(IStockMapper.class);

    Stock stockDtoToStock(StockDto stockDto);

    @InheritInverseConfiguration
    StockDto stockToStockDto(Stock stock);
}
