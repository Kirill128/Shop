package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.RoleDao;
import by.itacademy.shop.api.dto.user.RoleDto;
import by.itacademy.shop.api.mappers.RoleMapper;
import by.itacademy.shop.api.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        return RoleMapper.mapRoleToRoleDto(this.roleDao.create(RoleMapper.mapRoleDtoToRole(roleDto)));
    }

    @Override
    public RoleDto find(long id) {
        return RoleMapper.mapRoleToRoleDto(this.roleDao.find(id));
    }

    @Override
    public void update(RoleDto roleDto) {
        this.roleDao.update(RoleMapper.mapRoleDtoToRole(roleDto));
    }

    @Override
    public void delete(long id) {
        this.roleDao.delete(id);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return RoleMapper.mapRolesToRoleDtos(this.roleDao.findAll());
    }
}
