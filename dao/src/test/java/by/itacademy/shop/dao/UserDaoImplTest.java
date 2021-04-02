package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.entities.Role;
import by.itacademy.shop.entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class UserDaoImplTest {
    @Test
    public void create(){
        UserDao userDao=new UserDaoImpl();
        List<Role> roles=new ArrayList<>(1);
        roles.add(Role.builder()
                .id(4L)
                .name("Name")
                .build());
        userDao.create(User.builder()
                .id(9L)
                .name("test create")
                .email("teeee")
                .phone("ssss")
                .password("heeeeeel")
                .roles(roles)
                .build());
    }
    @Test
    public void find(){
        UserDao userDao=new UserDaoImpl();
        User user=userDao.find(1);
        System.out.println(user);
    }
    @Test
    public void delete(){
    }
    @Test
    public void update(){
    }
    @Test
    public void findAll(){

    }
}