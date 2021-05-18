package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.admin.AdminUserDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.entities.Order;
import by.itacademy.shop.entities.User;
import by.itacademy.shop.utilenum.Lang;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {
    public UserDto mapUserToUserDto(User source, Lang lang){
        if(source==null)return null;
        return UserDto.builder().
                id(source.getId()).
                name(source.getName()).
                email(source.getEmail()).
                phone(source.getPhone()).
                password(source.getPassword()).
                roles(RoleMapper.mapRolesToRoleDtos(source.getRoles())).
                orders(OrderMapper.mapOrdersToOrdersDto(source.getOrders(),lang)).
                build();
    }
    public User mapUserDtoToUser(UserDto source){
        if(source==null)return null;
        return User.builder().
                id(source.getId()).
                name(source.getName()).
                email(source.getEmail()).
                phone(source.getPhone()).
                password(source.getPassword()).
                roles(RoleMapper.mapRoleDtosToRoles(source.getRoles())).
                build();
    }
    public List<UserDto> mapUsersToUserDtos(List<User> source,Lang lang){
        if(source==null)return new ArrayList<>();
        return source.stream().map((e)->UserMapper.mapUserToUserDto(e,lang)).collect(Collectors.toList());
    }
    public List<User> mapUserDtosToUsers(List<UserDto> source){
        if(source==null)return new ArrayList<>();
        return source.stream().map(UserMapper::mapUserDtoToUser).collect(Collectors.toList());
    }

    public AdminUserDto mapUserToAdminUserDto(User source){
        if(source==null)return null;
        return AdminUserDto.builder().
                id(source.getId()).
                name(source.getName()).
                email(source.getEmail()).
                phone(source.getPhone()).
                password(source.getPassword()).
                roles(RoleMapper.mapRolesToRoleDtos(source.getRoles())).
                build();
    }
    public List<AdminUserDto> mapUsersToAdminUserDtos(List<User> source){
        if(source==null)return new ArrayList<>();
        return source.stream().map(UserMapper::mapUserToAdminUserDto).collect(Collectors.toList());
    }
}
