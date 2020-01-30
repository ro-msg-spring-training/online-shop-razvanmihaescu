package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.services.user_service.IUserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    IUserService userService;

    public UserDto login(@RequestBody UserDto userDto) {
        return null;
    }
}
