package ro.msg.learning.shop.services.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.CartDto;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.entities.Cart;
import ro.msg.learning.shop.entities.Roles;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.exceptions.UserNotFoundException;
import ro.msg.learning.shop.mappers.CartMapper;
import ro.msg.learning.shop.mappers.UserMapper;
import ro.msg.learning.shop.repositories.IRoleRepository;
import ro.msg.learning.shop.repositories.IUserRepository;
import ro.msg.learning.shop.services.cart_service.CartService;

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

    @Autowired
    CartService cartService;

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

    @Override
    public void updateUserCart(String username, List<CartDto> cartDto) {
        Optional<User> userOptional = userRepository.findFirstByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            clearUserCart(user);
            user.setCart(cartDto.stream().map(cart -> {
                Cart cartEntity = cartService.convertToEntity(cart);
                cartEntity.setUser(user);
                return cartEntity;
            }).collect(Collectors.toList()));
            userRepository.save(user);
        } else throw new UserNotFoundException(username);
    }

    public void clearUserCart(User user)
    {
        cartService.clearUserCart(user);
    }
}
