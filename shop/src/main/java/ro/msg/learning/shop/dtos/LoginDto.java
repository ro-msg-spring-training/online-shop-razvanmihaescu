package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {

    private String username;

    private String password;

    private String fullName;

    private String roles;

    private String cart;
}
