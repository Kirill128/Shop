package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.RoleDao;
import by.itacademy.shop.entities.Role;
import org.springframework.stereotype.Repository;

@Repository

public class RoleDaoImpl extends GenericDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl() {
        super(Role.class);
    }
}
