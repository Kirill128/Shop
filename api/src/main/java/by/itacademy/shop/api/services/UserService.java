package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.admin.AdminUserDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.utilenum.Lang;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user, Lang lang);
    UserDto find(long id,Lang lang);
    AdminUserDto findFullInfo(long id);
    UserDto findByEmail(String email,Lang lang);
    void update(UserDto user);
    void delete(long id);

    List<AdminUserDto> getAllUsers();

    void addProductToUserOrderList(String email,long productId);
}
