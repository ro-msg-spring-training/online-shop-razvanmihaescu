package ro.msg.learning.shop.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.LocationDto;
import ro.msg.learning.shop.entities.Location;

@Component
public class LocationMapper {
    @Autowired
    AddressMapper addressMapper;

    public LocationDto convertToDto(Location location) {
        return LocationDto.builder()
                .address(addressMapper.convertToDto(location.getAddress()))
                .name(location.getName())
                .build();
    }

    public Location convertToEntity(LocationDto locationDto) {
        return Location.builder()
                .address(addressMapper.convertToEntity(locationDto.getAddress()))
                .name(locationDto.getName())
                .build();
    }
}
