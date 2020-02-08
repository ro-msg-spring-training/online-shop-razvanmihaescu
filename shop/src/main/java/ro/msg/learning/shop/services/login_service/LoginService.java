package ro.msg.learning.shop.services.login_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.CredentialsDto;
import ro.msg.learning.shop.dtos.JwtDto;
import ro.msg.learning.shop.security.JwtTokenProvider;

@Service
public class LoginService implements ILoginService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Override
    public ResponseEntity<?> tryToLogin(CredentialsDto credentialsDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentialsDto.getUsername(),
                        credentialsDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtDto(token));
    }
}
