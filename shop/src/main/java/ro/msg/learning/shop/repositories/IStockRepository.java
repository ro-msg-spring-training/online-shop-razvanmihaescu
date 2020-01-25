package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Stock;

import java.util.List;
import java.util.Optional;

@Repository
public interface IStockRepository extends JpaRepository<Stock, Integer> {
    Optional<List<Stock>> getAllByLocation(Location location);
}
