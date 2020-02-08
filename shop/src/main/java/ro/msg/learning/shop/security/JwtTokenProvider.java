package ro.msg.learning.shop.security;

import com.auth0.jwt.JWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static ro.msg.learning.shop.security.SecurityConstants.EXPIRATION_TIME;
import static ro.msg.learning.shop.security.SecurityConstants.SECRET;

@Component
public class JwtTokenProvider {
    public String generateToken(Authentication authentication) {

        return JWT.create()
                .withSubject(((User) authentication.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
    }
}
