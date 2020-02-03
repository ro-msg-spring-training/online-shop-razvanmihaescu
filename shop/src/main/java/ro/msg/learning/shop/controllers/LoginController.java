package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.LoginDto;
import ro.msg.learning.shop.services.login_service.ILoginService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    ILoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginDto login(@RequestBody LoginDto loginDto) {
        return loginService.tryToLogin(loginDto);
    }
}
