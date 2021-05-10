package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
    UserDto find(long id);
    void update(UserDto user);
    void delete(long id);

    List<UserDto> getAllUsers();
}
