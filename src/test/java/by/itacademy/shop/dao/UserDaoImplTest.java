package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@Transactional
class UserDaoImplTest {

    @Autowired
    private UserDao userDao;


    @Test
    void userDaoNotNull() {
        Assertions.assertNotNull(userDao);
    }
    @Test
    void create() {
        User user = User.builder()
                .name("Some name")
                .email("Some email")
                .password("Bcrypt password")
                .phone("Phone")
                .build();
        this.userDao.create(user);
    }

    @Test
    void find() {
        User user=this.userDao.find(1L);
        Assertions.assertEquals(user.getName(),"anna");
        Assertions.assertEquals(user.getEmail(),"anna@gmail.com");
        Assertions.assertEquals(user.getPhone(),"+37525999999");
    }

    @Test
    void update() {
        String nameForUpdate="User with some name";
        User userToUpdate = this.userDao.find(1L);
        userToUpdate.setName(nameForUpdate);
        this.userDao.update(userToUpdate);
        Assertions.assertEquals(this.userDao.find(1L).getName(),nameForUpdate);
    }

    @Test
    void delete() {
        User userToDelete=this.userDao.find(1L);
        this.userDao.delete(userToDelete);
        Assertions.assertNull(this.userDao.find(1L));

    }

    @Test
    void findAll() {
        List<User> users = this.userDao.findAll();
        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    void findByEmail() {
        User user=this.userDao.findByEmail("anna@gmail.com");
        Assertions.assertEquals(user.getName(),"anna");
        Assertions.assertEquals(user.getEmail(),"anna@gmail.com");
        Assertions.assertEquals(user.getPhone(),"+37525999999");
    }
}