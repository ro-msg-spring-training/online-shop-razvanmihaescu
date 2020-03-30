package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.CartDto;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.services.user_service.IUserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Users")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/id/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }


    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
    }

    @PatchMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserCart(@PathVariable String username, @RequestBody List<CartDto> cartDto) {
        userService.updateUserCart(username,cartDto);
    }
}
