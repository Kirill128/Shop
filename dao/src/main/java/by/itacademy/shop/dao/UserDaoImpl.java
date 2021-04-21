package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.UserDao;
import by.itacademy.shop.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    protected UserDaoImpl() {
        super(User.class);
    }
}
