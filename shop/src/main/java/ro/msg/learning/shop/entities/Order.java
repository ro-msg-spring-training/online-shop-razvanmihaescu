package ro.msg.learning.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Location shippedFrom;

    @ManyToOne
    private User customer;

    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    private Location deliveryLocation;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;
}
