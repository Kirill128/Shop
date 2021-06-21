package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.admin.AdminUserDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.senla.microservices.constants.Lang;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user, Lang lang) throws JsonProcessingException;
    UserDto find(long id,Lang lang);
    AdminUserDto findFullInfo(long id) throws JsonProcessingException;
    UserDto findByEmail(String email,Lang lang);
    void setRole(AdminUserDto userDto);
    void deleteRole(AdminUserDto userDto);
    void update(UserDto user);
    void delete(long id);

    List<AdminUserDto> getAllUsers() throws JsonProcessingException;

    void addProductToUserOrderList(String email,long productId) throws JsonProcessingException;
}
