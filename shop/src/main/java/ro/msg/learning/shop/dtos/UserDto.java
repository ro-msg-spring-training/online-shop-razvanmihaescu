package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String roles;

    private List<CartDto> cart;
}
