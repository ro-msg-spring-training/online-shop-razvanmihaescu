package ro.msg.learning.shop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Roles {


    @Id
    private String name;

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<User> users;
}
