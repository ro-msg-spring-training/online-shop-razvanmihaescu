package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.CartDto;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.entities.Cart;
import ro.msg.learning.shop.entities.Roles;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.exceptions.UserNotFoundException;
import ro.msg.learning.shop.mappers.ICartMapper;
import ro.msg.learning.shop.mappers.IUserMapper;
import ro.msg.learning.shop.repositories.IRoleRepository;
import ro.msg.learning.shop.repositories.IUserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CartService cartService;

    public User getUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public User createUser(UserDto userDto) {
        User newUser = IUserMapper.INSTANCE.userDtoToUser(userDto);
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        Optional<Roles> role = roleRepository.findById(userDto.getRoles().getName());
        if (role.isPresent()) {
            newUser.setRoles(role.get());
        } else {
            newUser.setRoles(roleRepository.getOne("CUSTOMER"));
        }
        return userRepository.save(newUser);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Integer userId, UserDto userDto) {
        User newUser = IUserMapper.INSTANCE.userDtoToUser(userDto);
        newUser.setId(userId);
        return userRepository.save(newUser);
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findFirstByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(username);
        }
    }

    public void updateUserCart(String username, List<CartDto> cartDtoList) {
        Optional<User> userOptional = userRepository.findFirstByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            clearUserCart(user);
            user.setCart(cartDtoList.stream().map(cart -> {
                Cart cartEntity = ICartMapper.INSTANCE.cartDtoToCart(cart);
                cartEntity.setUser(user);
                return cartEntity;
            }).collect(Collectors.toList()));
            userRepository.save(user);
        } else throw new UserNotFoundException(username);
    }

    public void clearUserCart(User user) {
        cartService.clearUserCart(user);
    }
}
