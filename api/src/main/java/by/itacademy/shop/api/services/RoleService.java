package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.RoleDto;
import by.itacademy.shop.api.dto.UserDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto user);
    RoleDto find(long id);
    void update(RoleDto user);
    void delete(long id);

    List<RoleDto> getAllRoles();
}
