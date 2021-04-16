package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.UserDto;
import by.itacademy.shop.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    UserDto mapUserDto(User source);
    User mapUser(UserDto source);
}
