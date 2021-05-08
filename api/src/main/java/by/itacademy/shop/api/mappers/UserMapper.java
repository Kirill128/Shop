package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.entities.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {
    public UserDto mapUserToUserDto(User source){
        return UserDto.builder().
                id(source.getId()).
                name(source.getName()).
                email(source.getEmail()).
                phone(source.getPhone()).
                password(source.getPassword()).
                roles(RoleMapper.mapRolesToRoleDtos(source.getRoles())).
                build();
    }
    public User mapUserDtoToUser(UserDto source){
        return User.builder().
                id(source.getId()).
                name(source.getName()).
                email(source.getEmail()).
                phone(source.getPhone()).
                password(source.getPassword()).
                roles(RoleMapper.mapRoleDtosToRoles(source.getRoles())).
                build();
    }
    public List<UserDto> mapUsersToUserDtos(List<User> source){
        return source.stream().map(UserMapper::mapUserToUserDto).collect(Collectors.toList());
    }
    public List<User> mapUserDtosToUsers(List<UserDto> source){
        return source.stream().map(UserMapper::mapUserDtoToUser).collect(Collectors.toList());
    }
}
