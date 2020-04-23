package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.CredentialsDto;
import ro.msg.learning.shop.services.LoginService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody CredentialsDto credentialsDto) {
        return loginService.tryToLogin(credentialsDto);
    }

}
