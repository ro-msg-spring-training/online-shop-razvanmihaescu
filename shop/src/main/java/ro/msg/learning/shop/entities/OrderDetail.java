package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
public class OrderDetail {

    private Order order;

    private Product product;

    private Integer quantity;
}
