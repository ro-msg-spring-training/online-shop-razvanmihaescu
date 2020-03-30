package ro.msg.learning.shop.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.entities.User;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private CartMapper cartMapper;

    public UserDto convertToDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .username(user.getUsername())
                .roles(user.getRoles().getName())
                .cart(user.getCart().stream().map(cart -> cartMapper.convertToDto(cart)).collect(Collectors.toList()))
                .build();
    }

    public User convertToEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .cart(userDto.getCart().stream().map(cart -> cartMapper.convertToEntity(cart)).collect(Collectors.toList()))
                .build();
    }
}
