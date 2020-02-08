package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.CredentialsDto;
import ro.msg.learning.shop.services.login_service.ILoginService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    ILoginService loginService;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody CredentialsDto credentialsDto) {
        return loginService.tryToLogin(credentialsDto);
    }

}
