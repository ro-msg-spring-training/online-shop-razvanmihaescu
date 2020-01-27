package ro.msg.learning.shop.services.location_service;

import ro.msg.learning.shop.dtos.LocationDto;
import ro.msg.learning.shop.entities.Location;

import java.util.List;

public interface ILocationService {
    List<Location> getAllLocations();

    LocationDto convertToDto(Location location);

    Location convertToEntity(LocationDto locationDto);
}
