package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private RolesDto roles;

    private List<CartDto> cart;
}
