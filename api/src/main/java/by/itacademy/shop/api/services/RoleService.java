package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.admin.AdminRoleDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Set;

public interface RoleService {
    AdminRoleDto createRole(AdminRoleDto user) throws JsonProcessingException;
    AdminRoleDto find(long id);
    AdminRoleDto findByName(String name);
    void update(AdminRoleDto user);
    void delete(long id);

    Set<AdminRoleDto> getAllRoles();
}
