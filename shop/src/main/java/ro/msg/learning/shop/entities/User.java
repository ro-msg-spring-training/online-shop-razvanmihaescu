package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    @ManyToOne
    private Roles roles;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Orders> orders;
}
