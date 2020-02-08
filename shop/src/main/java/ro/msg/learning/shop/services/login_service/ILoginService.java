package ro.msg.learning.shop.services.login_service;

import org.springframework.http.ResponseEntity;
import ro.msg.learning.shop.dtos.CredentialsDto;

public interface ILoginService {
    ResponseEntity<?> tryToLogin(CredentialsDto credentialsDto);
}
