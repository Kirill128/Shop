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
        User u= User.builder()
                .id(200L)
                .name("Test Vasa")
                .email("Test Vasa")
                .password("Test Vasa")
                .roles(new ArrayList<>())
                .phone("Test Vasa")
                .build();
        u.getRoles().add(roleDao.find(2));
        userDao.create(u);
    }
    @Test
    public void find(){
        UserDao userDao=new UserDaoImpl();
        User user=userDao.find(200L);
        System.out.println(user);
    }
    @Test
    public void update(){
        RoleDao roleDao=new RoleDaoImpl();
        UserDao userDao=new UserDaoImpl();
        User u= User.builder()
                .id(200L)
                .name("Test Peta")
                .email("Test Peta")
                .password("Test Peta")
                .roles(new ArrayList<>())
                .phone("Test Peta")
                .build();
        u.getRoles().add(roleDao.find(1));
        userDao.update(u);
    }
    @Test
    public void delete(){
        UserDao userDao=new UserDaoImpl();
        User u=userDao.find(200L);
        userDao.delete(u);
    }
    @Test
    public void findAll(){

    }
    /*
    @Test
    public void create(){

    }
    @Test
    public void find(){

    }
    @Test
    public void update() {

    }
    @Test
    public void delete(){

    }

    @Test
    public void findAll(){

    }
    */
}