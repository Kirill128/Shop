package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.RoleDao;
import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.entities.Role;
import by.itacademy.shop.entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class UserDaoImplTest {
    @Test
    public void create(){
        RoleDao roleDao=new RoleDaoImpl();
        UserDao userDao=new UserDaoImpl();
        User u=new User(19L,"role create","teeee","ssss","heeeeeel");
        u.getRoles().add(roleDao.find(2));
        userDao.create(u);
    }
    @Test
    public void find(){
        UserDao userDao=new UserDaoImpl();
        User user=userDao.find(2);
        System.out.println(user);
    }
    @Test
    public void delete(){
    }
    @Test
    public void update(){
        RoleDao roleDao=new RoleDaoImpl();
        UserDao userDao=new UserDaoImpl();
        User u=new User(9L,"test after update","teeee","ssss","heeeeeel");
        u.getRoles().add(roleDao.find(2));
        u.getRoles().add(roleDao.find(1));
        userDao.update(u);
    }
    @Test
    public void findAll(){

    }
}