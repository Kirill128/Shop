package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto find(long id);
    void update(UserDto user);
    void delete(long id);

    List<UserDto> getAllUsers();
}
