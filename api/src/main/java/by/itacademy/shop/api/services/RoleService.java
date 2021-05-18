package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.admin.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto user);
    RoleDto find(long id);
    RoleDto findByName(String name);
    void update(RoleDto user);
    void delete(long id);

    List<RoleDto> getAllRoles();
}
