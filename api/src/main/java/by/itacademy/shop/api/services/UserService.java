package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto find(int id);
    void update(UserDto user);
    void delete(int id);

    List<UserDto> getAllUsers();
}
