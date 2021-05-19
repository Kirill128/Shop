package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.RoleDao;
import by.itacademy.shop.api.dto.admin.AdminRoleDto;
import by.itacademy.shop.api.mappers.RoleMapper;
import by.itacademy.shop.api.services.RoleService;
import by.itacademy.shop.entities.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public AdminRoleDto createRole(AdminRoleDto roleDto) {
        return RoleMapper.mapRoleToRoleDto(this.roleDao.create(RoleMapper.mapRoleDtoToRole(roleDto)));
    }

    @Override
    public AdminRoleDto find(long id) {
        return RoleMapper.mapRoleToRoleDto(this.roleDao.find(id));
    }

    @Override
    public AdminRoleDto findByName(String name) {
        return RoleMapper.mapRoleToRoleDto(this.roleDao.findByName(name));
    }

    @Override
    public void update(AdminRoleDto roleDto) {
        this.roleDao.update(RoleMapper.mapRoleDtoToRole(roleDto));
    }

    @Override
    public void delete(long id) {
        Role role=this.roleDao.find(id);
        this.roleDao.delete(role);
    }

    @Override
    public Set<AdminRoleDto> getAllRoles() {
        return RoleMapper.mapRolesToRoleDtos(new HashSet<>(this.roleDao.findAll()));
    }
}
