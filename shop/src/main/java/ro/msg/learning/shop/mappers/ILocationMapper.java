package ro.msg.learning.shop.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dtos.LocationDto;
import ro.msg.learning.shop.entities.Location;

@Mapper
public interface ILocationMapper {

    ILocationMapper INSTANCE = Mappers.getMapper(ILocationMapper.class);

    Location locationDtoToLocation(LocationDto locationDto);

    @InheritInverseConfiguration
    LocationDto locationToLocationDto(Location location);
}
