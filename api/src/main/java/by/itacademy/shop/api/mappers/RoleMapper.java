package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.user.RoleDto;
import by.itacademy.shop.entities.Role;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RoleMapper {
    public RoleDto mapRoleToRoleDto(Role source){
        return RoleDto.builder().
                id(source.getId()).
                name(source.getName()).
                build();
    }
    public Role mapRoleDtoToRole(RoleDto source){
        return Role.builder().
                id(source.getId()).
                name(source.getName()).
                build();
    }
    public List<RoleDto> mapRolesToRoleDtos(List<Role> source){
        return source.stream().map(RoleMapper::mapRoleToRoleDto).collect(Collectors.toList());
    }
    public List<Role> mapRoleDtosToRoles(List<RoleDto> source){
        return source.stream().map(RoleMapper::mapRoleDtoToRole).collect(Collectors.toList());
    }
    public List<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(e->new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());
    }
}
