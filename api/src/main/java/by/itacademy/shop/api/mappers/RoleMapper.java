package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.admin.RoleDto;
import by.itacademy.shop.entities.Role;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RoleMapper {
    public RoleDto mapRoleToRoleDto(Role source){
        if(source==null)return null;
        return RoleDto.builder().
                id(source.getId()).
                name(source.getName()).
                build();
    }
    public Role mapRoleDtoToRole(RoleDto source){
        if(source==null)return null;
        return Role.builder().
                id(source.getId()).
                name(source.getName()).
                build();
    }
    public List<RoleDto> mapRolesToRoleDtos(List<Role> source){
        if(source==null)return new ArrayList<>();
        return source.stream().map(RoleMapper::mapRoleToRoleDto).collect(Collectors.toList());
    }
    public List<Role> mapRoleDtosToRoles(List<RoleDto> source){
        if(source==null)return new ArrayList<>();
        return source.stream().map(RoleMapper::mapRoleDtoToRole).collect(Collectors.toList());
    }
    public List<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        if(roles==null)return new ArrayList<>();
        return roles.stream().map(e->new SimpleGrantedAuthority(e.getName())).collect(Collectors.toList());
    }
}
