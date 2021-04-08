package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.RoleDao;
import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.entities.Role;
import by.itacademy.shop.entities.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class RoleDaoImplTest {
    @Test
    public void create(){
        RoleDao roleDao=new RoleDaoImpl();
        Role role= Role.builder()
                .id(1L)
                .name("ROLE_TEST_CREATE")
                .build();
        roleDao.create(role);
    }
    @Test
    public void find(){
        RoleDao roleDao=new RoleDaoImpl();
        System.out.println(roleDao.find(1L));
    }
    @Test
    public void update() {
        RoleDao roleDao=new RoleDaoImpl();
        Role role= Role.builder()
                .id(1L)
                .name("ROLE_TEST_UPDATE")
                .build();
        roleDao.update(role);
    }
    @Test
    public void delete(){
        RoleDao roleDao=new RoleDaoImpl();
        Role role=roleDao.find(4L);
        roleDao.delete(role);
    }
    @Test
    public void addUsersForRole(){
        RoleDao roleDao=new RoleDaoImpl();
        UserDao userDao=new UserDaoImpl();
        Role role= Role.builder()
                .id(4L)
                .name("ROLE_TEST_CREATE")
                .build();
        roleDao.create(role);
        User user= User.builder()
                .id(100L)
                .name("Test role create")
                .email("Test role create")
                .password("Test role create")
                .roles(new ArrayList<>())
                .build();
        user.getRoles().add(role);
        userDao.create(user);

    }

    @Test
    public void checkUsers(){
        RoleDao roleDao=new RoleDaoImpl();
        Role role=roleDao.find(4L);
        role.getUsers().stream().forEach(System.out::println);
    }

    @Test
    public void findAll(){

    }
}