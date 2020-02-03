package ro.msg.learning.shop.services.login_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.LoginDto;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.entities.User;
import ro.msg.learning.shop.exceptions.IncorrectPasswordException;
import ro.msg.learning.shop.exceptions.UserNotFoundException;
import ro.msg.learning.shop.mappers.UserMapper;
import ro.msg.learning.shop.repositories.IUserRepository;

import java.util.Optional;

@Service
public class LoginService implements ILoginService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public LoginDto tryToLogin(LoginDto loginDto) {
        Optional<User> user = userRepository.findFirstByUsername(loginDto.getUsername());
        if(user.isPresent())
        {
            UserDto userDto=userMapper.convertToDto(user.get());
            if(userDto.getPassword().equals(loginDto.getPassword()))
            {
                loginDto.setCart("CART");
                loginDto.setFullName(userDto.getFirstName()+" "+userDto.getLastName());
                loginDto.setRoles(userDto.getRolesDto().getName());
                return loginDto;
            }
            else {
                throw  new IncorrectPasswordException();
            }
        }
        else throw new UserNotFoundException(loginDto.getUsername());
    }
}
