package ro.msg.learning.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles {

    @Id
    private String name;

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;
}
