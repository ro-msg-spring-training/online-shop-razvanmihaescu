package ro.msg.learning.shop.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CredentialsDto {
    private String username;
    private String password;
}
