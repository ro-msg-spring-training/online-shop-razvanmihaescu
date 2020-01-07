package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "shipped_from_id", referencedColumnName = "id")
    private Location shippedFrom;

    @ManyToOne
    private User customer;

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "id")
    private Address deliveryAddress;
}
