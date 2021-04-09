package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.RoleDao;
import by.itacademy.shop.entities.Role;

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    protected RoleDaoImpl() {
        super(Role.class);
    }
}
