package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Stock;

import java.util.List;

@Repository
public interface IStockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findAllByLocation_Id(Integer locationId);

    Stock findByLocation_NameAndProduct_Name(String location_name, String product_name);

    List<Stock> findStocksByProduct_Id(Integer product_id);
}
