package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.admin.AdminRoleDto;
import by.itacademy.shop.entities.Role;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class RoleMapper {
    public AdminRoleDto mapRoleToRoleDto(Role source){
        if(source==null)return null;
        return AdminRoleDto.builder().
                id(source.getId()).
                name(source.getName()).
                build();
    }
    public Role mapRoleDtoToRole(AdminRoleDto source){
        if(source==null)return null;
        return Role.builder().
                id(source.getId()).
                name(source.getName()).
                build();
    }
    public Set<AdminRoleDto> mapRolesToRoleDtos(Set<Role> source){
        if(source==null)return new HashSet<>();
        return source.stream().map(RoleMapper::mapRoleToRoleDto).collect(Collectors.toSet());
    }
    public Set<Role> mapRoleDtosToRoles(Set<AdminRoleDto> source){
        if(source==null)return new HashSet<>();
        return source.stream().map(RoleMapper::mapRoleDtoToRole).collect(Collectors.toSet());
    }
    public List<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        if(roles==null)return new ArrayList<>();
        return roles.stream().map(e->new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());
    }
    public List<GrantedAuthority> mapRoleDtosToAuthorities(Collection<AdminRoleDto> roles){
        if(roles==null)return new ArrayList<>();
        return roles.stream().map(e->new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());
    }

}
