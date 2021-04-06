package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.RoleDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleDaoImplTest {
    @Test
    public void delete(){
        RoleDao roleDao=new RoleDaoImpl();
        roleDao.delete(roleDao.find(1));
    }

}