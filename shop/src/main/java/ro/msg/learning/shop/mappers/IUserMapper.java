package ro.msg.learning.shop.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dtos.UserDto;
import ro.msg.learning.shop.entities.User;

@Mapper
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User userDtoToUser(UserDto userDto);

    @InheritInverseConfiguration
    UserDto userToUserDto(User user);
}
