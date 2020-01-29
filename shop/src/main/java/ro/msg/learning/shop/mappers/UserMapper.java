package ro.msg.learning.shop.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.entities.User;

@Component
public class UserMapper {

    @Autowired
    RolesMapper rolesMapper;

    public UserDto convertToDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .username(user.getUsername())
                .rolesDto(rolesMapper.convertToDto(user.getRoles()))
                .build();
    }

    public User convertToEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .roles(rolesMapper.convertToEntity(userDto.getRolesDto()))
                .build();
    }
}
