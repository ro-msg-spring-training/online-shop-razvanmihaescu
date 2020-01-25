package ro.msg.learning.shop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class ProductCategory {

    @Id
    @TableGenerator(name = "mySeqGen", initialValue = 10, allocationSize = 100)
    @GeneratedValue(generator = "mySeqGen")
    private Integer id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}
