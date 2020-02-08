package ro.msg.learning.shop.dtos;

import lombok.Data;

import static ro.msg.learning.shop.security.SecurityConstants.TOKEN_PREFIX;

@Data
public class JwtDto {
    private String accessToken;
    private String tokenType = TOKEN_PREFIX;

    public JwtDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
