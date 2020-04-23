package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.CartDto;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.mappers.IUserMapper;
import ro.msg.learning.shop.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Users")
public class UserController {

    private final UserService userService;

    @GetMapping("/id/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto getUserById(@PathVariable Integer userId) {
        return IUserMapper.INSTANCE.userToUserDto(userService.getUserById(userId));
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto getUserByUsername(@PathVariable String username) {
        return IUserMapper.INSTANCE.userToUserDto(userService.getUserByUsername(username));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto addUser(@RequestBody UserDto userDto) {
        return IUserMapper.INSTANCE.userToUserDto(userService.createUser(userDto));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getUsers() {
        return userService.getUsers().stream().map(IUserMapper.INSTANCE::userToUserDto).collect(Collectors.toList());
    }


    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        return IUserMapper.INSTANCE.userToUserDto(userService.updateUser(userId, userDto));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
    }

    @PatchMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserCart(@PathVariable String username, @RequestBody List<CartDto> cartDto) {
        userService.updateUserCart(username, cartDto);
    }
}
