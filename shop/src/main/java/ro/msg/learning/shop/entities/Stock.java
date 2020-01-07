package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
public class Stock {

    @Id
    private Integer id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Location location;

    private Integer quantity;
}
