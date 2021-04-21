package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.RoleDto;
import by.itacademy.shop.entities.Role;
import org.mapstruct.factory.Mappers;

public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper( RoleMapper.class );
    RoleDto mapUserDto(Role source);
    Role mapUser(RoleDto source);
}
