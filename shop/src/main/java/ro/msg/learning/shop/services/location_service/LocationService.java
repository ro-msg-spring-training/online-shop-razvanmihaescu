package ro.msg.learning.shop.services.location_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.LocationDto;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.mappers.LocationMapper;
import ro.msg.learning.shop.repositories.ILocationRepository;

import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Autowired
    ILocationRepository locationRepository;

    @Autowired
    LocationMapper locationMapper;

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public LocationDto convertToDto(Location location) {
        return locationMapper.convertToDto(location);
    }

    @Override
    public Location convertToEntity(LocationDto locationDto) {
        return locationMapper.convertToEntity(locationDto);
    }
}
