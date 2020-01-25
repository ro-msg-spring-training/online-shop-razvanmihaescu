package ro.msg.learning.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @TableGenerator(name = "mySeqGen", initialValue = 50, allocationSize = 100)
    @GeneratedValue(generator = "mySeqGen")
    private Integer productId;

    private String name;

    private String description;

    private Float price;

    private Double weight;

    @ManyToOne
    private ProductCategory category;

    private String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks;
}
