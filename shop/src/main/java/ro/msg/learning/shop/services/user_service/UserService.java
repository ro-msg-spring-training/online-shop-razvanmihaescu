package ro.msg.learning.shop.services.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.RolesDto;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.entities.Roles;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.exceptions.UserNotFoundException;
import ro.msg.learning.shop.mappers.UserMapper;
import ro.msg.learning.shop.repositories.IRoleRepository;
import ro.msg.learning.shop.repositories.IUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    IRoleRepository roleRepository;


    @Override
    public UserDto getUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return convertToDto(user.get());
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User newUser = userMapper.convertToEntity(userDto);
        Optional<Roles> role = roleRepository.findById(userDto.getRolesDto().getName());
        if (role.isPresent()) {
            newUser.setRoles(role.get());
        } else {
            newUser.setRoles(roleRepository.getOne("CUSTOMER"));
        }
        return convertToDto(userRepository.save(newUser));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto convertToDto(User user) {
        return userMapper.convertToDto(user);
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        return userMapper.convertToEntity(userDto);
    }
}
