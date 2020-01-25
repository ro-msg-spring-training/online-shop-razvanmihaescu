package ro.msg.learning.shop.services.location_service;

import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Location;

import java.util.List;

@Service
public interface ILocationService {
    List<Location> getAllLocations();
}
