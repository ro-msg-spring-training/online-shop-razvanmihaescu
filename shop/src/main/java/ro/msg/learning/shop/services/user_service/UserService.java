package ro.msg.learning.shop.services.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


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
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        Optional<Roles> role = roleRepository.findById(userDto.getRoles());
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

    @Override
    public UserDto updateUser(Integer userId, UserDto userDto) {
        User user = convertToEntity(userDto);
        user.setId(userId);
        User persistedUser = userRepository.save(user);
        return convertToDto(persistedUser);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<User> user = userRepository.findFirstByUsername(username);
        if (user.isPresent()) {
            return convertToDto(user.get());
        } else {
            throw new UserNotFoundException(username);
        }
    }
}
