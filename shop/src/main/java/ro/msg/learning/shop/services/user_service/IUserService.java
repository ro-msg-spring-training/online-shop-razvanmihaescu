package ro.msg.learning.shop.services.user_service;

import ro.msg.learning.shop.dtos.CartDto;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.entities.User;

import java.util.List;

public interface IUserService {

    UserDto getUserById(Integer userId);

    UserDto createUser(UserDto userDto);

    List<UserDto> getUsers();

    UserDto convertToDto(User user);

    User convertToEntity(UserDto userDto);

    UserDto updateUser(Integer userId, UserDto userDto);

    void deleteUser(Integer userId);

    UserDto getUserByUsername(String username);

    void updateUserCart(String username, List<CartDto> cartDto);
}
